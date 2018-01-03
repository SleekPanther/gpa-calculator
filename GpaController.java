import java.net.URL;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import java.util.*;

public class GpaController implements Initializable {
	private static final int DECIMAL_PRECISION = 2;		//how many decimals to round to

	private GpaModel model = new GpaModel();

	private ArrayList<String> gradesOptions = new ArrayList<String>();
	private ArrayList<Class> classes = new ArrayList<Class>();

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

		@FXML private HBox calcPane;
			@FXML private Label gpaOverall;
			@FXML private Button resetButton;
	//

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
		gradesOptions.add("F");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int rowOffset = 1;
		for(int i=0+rowOffset; i<8+rowOffset; i++){
			TextField title = new TextField();
			title.setId("class"+i+"Title");

			Pane gradeContainer = new Pane();
			gradeContainer.getStyleClass().addAll("gradeColumn");

			ComboBox<String> gradeDropdown = new ComboBox<String>();
			gradeDropdown.setItems(FXCollections.observableArrayList(gradesOptions));
			gradeDropdown.setValue(gradesOptions.get(0));
			gradeContainer.getChildren().addAll(gradeDropdown);

			TextField credits = new TextField();
			credits.setId("class"+i+"Credits");
			credits.getStyleClass().addAll("creditsColumn");

			Pane qualityPointsContainer = new Pane();
			qualityPointsContainer.getStyleClass().addAll("qualityPointsColumn");
			
			Label qualityPoints = new Label();
			qualityPoints.setId("class"+i+"Points");
			qualityPointsContainer.getChildren().addAll(qualityPoints);

			classesPane.add(title, 0, i);
			classesPane.add(gradeContainer, 1, i);
			classesPane.add(credits, 2, i);
			classesPane.add(qualityPointsContainer, 3, i);

			classes.add(new Class(title, gradeDropdown, credits, qualityPoints));
		}

		//Register event handlers & listeners
		for(int i=0; i<classes.size(); i++){
			Class currentClass = classes.get(i);
			currentClass.grade.setItems(FXCollections.observableArrayList(gradesOptions));
			currentClass.grade.setValue(gradesOptions.get(0));

			currentClass.grade.setOnAction(e -> validateAndCalculateClass(currentClass));
			
			currentClass.credits.focusedProperty().addListener(new ClassTextFieldListener(currentClass));
			currentClass.credits.setOnAction(e -> validateAndCalculateClass(currentClass));

			currentClass.credits.textProperty().addListener(new PositiveIntegerTextFieldListener(currentClass.credits));
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

	//Validates the class currently in focus (class that the grade/credits belong to) & calculates GPA if all classes are valid (setting appropriate error labels)
	public void validateAndCalculateClass(Class classObj){
		//Validate current class & only print error for current class (avoids printing a ton of errors at the beginning)
		if(model.isClassValid(classObj)){
			model.setQualityPoints(classObj);
		}
		else{
			classObj.qualityPointsLabel.setText("Credits can't be empty");
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
		else if(!GpaModel.isEmptyString(currentGPA.getText())){
			currentGPAError.setText(model.getGPAErrorIfInvalid(currentGPA.getText()));
		}

		model.calcGpaOverall(classes, currentGPA, currentCredits);
		gpaOverall.setText(round(model.getGpaOverall(), DECIMAL_PRECISION)+"");
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

		gpaOverall.setText("0.0");

		classes.get(0).credits.requestFocus();
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

}