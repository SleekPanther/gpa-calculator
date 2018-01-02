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

	private ArrayList<String> grades = new ArrayList<String>();
	private ArrayList<Class> classes = new ArrayList<Class>();

	// @FXML private VBox mainPane;
	// 	@FXML private GridPane classesPane;
		@FXML private ComboBox<String> class1Grade;
		@FXML private TextField class1Credits;
		@FXML private Label class1Points;

		@FXML private ComboBox<String> class2Grade;
		@FXML private TextField class2Credits;
		@FXML private Label class2Points;

		@FXML private TextField currentGPA;
		@FXML private Label currentGPAError;

	// 	@FXML private VBox calcPane;
			@FXML private Button calcGpaOverallButton;
			@FXML private Label gpaOverallLabel;
			@FXML private Button resetButton;
	//

	public GpaController(){
		grades.add("A+");
		grades.add("A");
		grades.add("A-");
		grades.add("B+");
		grades.add("B");
		grades.add("B-");
		grades.add("C+");
		grades.add("C");
		grades.add("C-");
		grades.add("D+");
		grades.add("F");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		classes.add(new Class(class1Grade, class1Credits, class1Points));
		classes.add(new Class(class2Grade, class2Credits, class2Points));

		for(int i=0; i<classes.size(); i++){
			Class currentClass = classes.get(i);
			currentClass.grade.setItems(FXCollections.observableArrayList(grades));
			currentClass.grade.setValue(grades.get(0));

			currentClass.grade.setOnAction(e -> validateAndCalculateClass(currentClass));
			
			currentClass.credits.focusedProperty().addListener(new ClassTextFieldListener(currentClass));
			currentClass.credits.setOnAction(e -> validateAndCalculateClass(currentClass));

			//Only allow integer credits
			currentClass.credits.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						class1Credits.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});
		}

		currentGPA.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*(\\.\\d*)?")) {
					currentGPA.setText(oldValue);
				}
			}
		});

		currentGPA.focusedProperty().addListener(new TextFieldListener(currentGPA, currentGPAError, "currentGPA"));

		Platform.runLater(() -> class1Credits.requestFocus());		//Request focus must be AFTER he FXML is finished loading
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
		
		model.calcGpaOverall(classes);
		gpaOverallLabel.setText(round(model.getGpaOverall(), DECIMAL_PRECISION)+"");
	}

	@FXML
	public void handleCalcGpaOverallButton(ActionEvent event){
		model.calcGpaOverall(classes);
		gpaOverallLabel.setText(round(model.getGpaOverall(), DECIMAL_PRECISION)+"");
	}

	@FXML
	public void handleResetButton(ActionEvent event){
		model.reset();
		gpaOverallLabel.setText("0.0");
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

	class TextFieldListener implements ChangeListener<Boolean> {
		private final TextField textField;
		private final Label errorLabel;
		private String type;

		public TextFieldListener(TextField textField, Label errorLabel) {
			this(textField, errorLabel, "");
		}
		public TextFieldListener(TextField textField, Label errorLabel, String type) {
			this.textField = textField;
			this.errorLabel = errorLabel;
			this.type=type;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			if(!newValue){		// check if focus gained or lost
				String errorText = "";
				if(type.equals("currentGPA")){
					errorText=model.getGPAErrorIfInvalid(textField.getText());
					errorLabel.setText(errorText);		//Always set error text (clears error if they fixed the mistake)
					if(errorText.equals("")){
						System.out.println("Existing gpa valid");
					}
				}else{
					//For other TextFields, optonally check if invalid & change errText
					errorLabel.setText(textField.getText()+errorText);
				}
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