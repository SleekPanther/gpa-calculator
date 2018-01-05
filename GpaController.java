import java.net.URL;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import java.util.*;
import java.text.DecimalFormat;

public class GpaController implements Initializable {
	private static final int DECIMAL_PRECISION = 2;		//how many decimals to round to
	private DecimalFormat numberFormatter = new DecimalFormat("0.00");

	private GpaModel model = new GpaModel();

	private ArrayList<String> gradesOptions = new ArrayList<String>();
	private ArrayList<Class> classes = new ArrayList<Class>();

	private final int INITIAL_NUMBER_OF_CLASSES = 8;
	private final int ROW_OFFSET = 1;	//Row 1 defined in FXML for column headers
	private int nextFreeClassRow = ROW_OFFSET;	//next available row to insert a class

	// @FXML private VBox mainPane;
	@FXML private GridPane classesPane;
		@FXML private TextField class1Title;
		@FXML private ComboBox<String> class1Grade;
		@FXML private TextField class1Credits;
		@FXML private Label class1Points;

		@FXML private TextField class2Title;
		@FXML private ComboBox<String> class2Grade;
		@FXML private TextField class2Credits;
		@FXML private Label class2Points;

		@FXML private TextField currentGPA;
		@FXML private Label currentGPAError;
		@FXML private TextField currentCredits;
		@FXML private Label currentCreditsError;

		@FXML private Button addClassButton;

		@FXML private Label gpaOverall;
		@FXML private Button resetButton;

	public GpaController(){
		gradesOptions.add("A+");
		gradesOptions.add("A");
		gradesOptions.add("A-");
		gradesOptions.add("B+");
		gradesOptions.add("B");
		gradesOptions.add("B-");
		gradesOptions.add("C+");
		gradesOptions.add("C");
		gradesOptions.add("C-");
		gradesOptions.add("D+");
		gradesOptions.add("D");
		gradesOptions.add("D-");
		gradesOptions.add("F");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int row=0+ROW_OFFSET; row<INITIAL_NUMBER_OF_CLASSES+ROW_OFFSET; row++){
			Class newClass = addClass(row);
			registerEventHandlers(newClass);
		}

		//Only allow decimal numbers
		currentGPA.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*(\\.\\d*)?")) {
					currentGPA.setText(oldValue);
				}
			}
		});

		addClassButton.setFocusTraversable(false);

		currentCredits.textProperty().addListener(new PositiveIntegerTextFieldListener(currentCredits));

		currentGPA.setOnAction(e -> calcGpa());
		currentGPA.focusedProperty().addListener((observable, oldValue, newValue) -> {
			calcGpa();
		});

		currentCredits.setOnAction(e -> calcGpa());
		currentCredits.focusedProperty().addListener((observable, oldValue, newValue) -> {
			calcGpa();
		});


		Platform.runLater(() -> classes.get(0).credits.requestFocus());		//Request focus must be AFTER he FXML is finished loading
	}

	private Class addClass(int row){
		nextFreeClassRow++;

		TextField title = new TextField();
		title.getStyleClass().addAll("titleColumn");

		Pane gradeContainer = new Pane();
		gradeContainer.getStyleClass().addAll("gradeColumn");

		ComboBox<String> gradeDropdown = new ComboBox<String>();
		gradeDropdown.setItems(FXCollections.observableArrayList(gradesOptions));
		gradeDropdown.setVisibleRowCount(gradesOptions.size());
		gradeDropdown.setValue(gradesOptions.get(0));
		gradeContainer.getChildren().addAll(gradeDropdown);

		TextField credits = new TextField();
		credits.setId("class"+row+"Credits");
		credits.getStyleClass().addAll("creditsColumn");

		Pane qualityPointsContainer = new Pane();
		qualityPointsContainer.getStyleClass().addAll("qualityPointsColumn");
		
		Label qualityPoints = new Label();
		qualityPoints.setId("class"+row+"Points");
		qualityPointsContainer.getChildren().addAll(qualityPoints);

		Button removeButton = new Button("Remove");
		removeButton.getStyleClass().addAll("removeColumn", "removeButton");
		removeButton.setFocusTraversable(false);

		classesPane.add(title, 0, row);
		classesPane.add(gradeContainer, 1, row);
		classesPane.add(credits, 2, row);
		classesPane.add(qualityPointsContainer, 3, row);
		classesPane.add(removeButton, 4, row);

		Class newClass = new Class(row, title, gradeDropdown, credits, qualityPoints, removeButton);
		classes.add(newClass);

		return newClass;
	}

	private void registerEventHandlers(Class newClass){
		newClass.grade.setItems(FXCollections.observableArrayList(gradesOptions));
		newClass.grade.setValue(gradesOptions.get(0));

		newClass.grade.setOnAction(e -> validateAndCalculateClass(newClass));
		
		newClass.credits.focusedProperty().addListener(new ClassTextFieldListener(newClass));
		newClass.credits.setOnAction(e -> validateAndCalculateClass(newClass));

		newClass.credits.textProperty().addListener(new PositiveIntegerTextFieldListener(newClass.credits));

		newClass.removeButton.setOnAction(e -> removeClass(newClass.id));
	}

	private void removeClass(int row){
		Set<Node> deleteNodes = new HashSet<Node>();
		for (Node child : classesPane.getChildren()) {
			Integer rowIndex = GridPane.getRowIndex(child);		// get index from child

			// handle null values for index=0
			int r = rowIndex == null ? 0 : rowIndex;

			if (r > row) {			// decrement rows for rows after the deleted row
				GridPane.setRowIndex(child, r-1);
			}
			else if (r == row) {		// collect matching rows for deletion
				deleteNodes.add(child);
			}
		}

		classesPane.getChildren().removeAll(deleteNodes);	// remove nodes from row

		//decrement row IDs for Class objects BELOW the deleted row
		for(int i=row; i<classes.size(); i++){
			classes.get(i).id = classes.get(i).id -1;
		}

		classes.remove(row-1);		//Remove from class list (-1 offset since GUI starts at row=1 & arrays count from 0)

		nextFreeClassRow--;		//decrement next available row since 1 was removed
		
		((Stage)classesPane.getScene().getWindow()).sizeToScene();
	}

	public void handleAddClassButton(ActionEvent event){
		Class newClass = addClass(nextFreeClassRow);		//add new class at current available row
		registerEventHandlers(newClass);
		
		((Stage)classesPane.getScene().getWindow()).sizeToScene();
	}

	//Validates the class currently in focus (class that the grade/credits belong to) & calculates GPA if all classes are valid (setting appropriate error labels)
	public void validateAndCalculateClass(Class classObj){
		//Validate current class & only print error for current class (avoids printing a ton of errors at the beginning)
		if(model.isClassValid(classObj)){
			model.setQualityPoints(classObj);
			classObj.qualityPointsLabel.getStyleClass().removeAll("errorLabel");
		}
		else{
			classObj.qualityPointsLabel.setText("Credits can't be empty");
			classObj.qualityPointsLabel.getStyleClass().addAll("errorLabel");
		}
		
		calcGpa();
	}

	public void calcGpa(){
		//Clear existing error text
		currentGPAError.setText("");
		currentCreditsError.setText("");

		//Display error if only 1 field has a value
		if(GpaModel.isEmptyString(currentGPA.getText()) && !GpaModel.isEmptyString(currentCredits.getText())){
			currentGPAError.setText("Current GPA required");
		}
		else if(!GpaModel.isEmptyString(currentGPA.getText()) && GpaModel.isEmptyString(currentCredits.getText())){
			currentCreditsError.setText("Current Credits required");
		}
		else if(!GpaModel.isEmptyString(currentGPA.getText())){				//Else check number is in bounds
			currentGPAError.setText(model.getGPAErrorIfInvalid(currentGPA.getText()));
		}

		model.calcGpaOverall(classes, currentGPA, currentCredits);
		gpaOverall.setText(numberFormatter.format(roundDown(model.getGpaOverall(), DECIMAL_PRECISION)));
	}

	@FXML
	public void handleResetButton(ActionEvent event){
		model.reset();

		for(Class c : classes){
			c.title.setText("");
			c.grade.setValue(gradesOptions.get(0));
			c.credits.setText("");
			c.qualityPointsLabel.setText("");
		}

		currentGPA.setText("");
		currentGPAError.setText("");

		currentCredits.setText("");
		currentCreditsError.setText("");

		gpaOverall.setText("0.00");

		if(!classes.isEmpty()){
			classes.get(0).credits.requestFocus();
		}
	}


	class ClassTextFieldListener implements ChangeListener<Boolean> {
		private Class classObj;

		public ClassTextFieldListener(Class classObj) {
			this.classObj=classObj;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			if(!newValue){		// check if focus gained or lost
				validateAndCalculateClass(classObj);
			}
		}
	}

	class PositiveIntegerTextFieldListener implements ChangeListener<String> {
		private TextField textField;

		public PositiveIntegerTextFieldListener(TextField textField){
			this.textField = textField;
		}

		@Override		//Only allow positive integers
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if (!newValue.matches("\\d*")) {
				textField.setText(newValue.replaceAll("\\D", ""));
			}
			if(newValue.matches("0+")){
				textField.setText(oldValue);
			}
		}
	}


	//auto-convert numbers to text for setting GUI elements
	public static String numToText(double number){
		return numToText(number + "");
	}
	//String parameter version just in case argument type changes
	public static String numToText(String number){
		return number;
	}

	public static double round(double number, int decimals){
		double powerOfTen= Math.pow(10, decimals);		//must be a double to avoid integer division truncation errors
		return Math.round(number * powerOfTen ) / powerOfTen;
	}

	public static double roundDown(double number, int decimals){
		double powerOfTen= Math.pow(10, decimals);		//must be a double to avoid integer division truncation errors
		return Math.floor(number * powerOfTen) / powerOfTen;
	}

}