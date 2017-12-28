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
	private GpaModel model = new GpaModel();

	private ArrayList<String> grades = new ArrayList<String>();
	private ArrayList<Class> classes = new ArrayList<Class>();

	// @FXML private VBox mainPane;
	// 	@FXML private GridPane classesPane;
		@FXML private ComboBox<String> class1Grade;
		@FXML private Label class1GradeError;

		@FXML private TextField class1Credits;
		@FXML private Label class1CreditsError;

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
		class1Grade.setItems(FXCollections.observableArrayList(grades));
		class1Grade.setValue(grades.get(0));

		classes.add(new Class(class1Grade, class1Credits));
		
		// class1Grade.textProperty().addListener((observable, oldValue, newValue) -> {
		// 	gpaOverallLabel.setText( class1Grade.getText() );
		// });

		// class1Grade.focusedProperty().addListener(new TextFieldListener(class1Grade, class1GradeError));
		class1Credits.focusedProperty().addListener(new TextFieldListener(class1Credits, class1CreditsError));
		currentGPA.focusedProperty().addListener(new TextFieldListener(currentGPA, currentGPAError, "currentGPA"));

		//Also handle acents when pressing Enter, not many buttons

		//Request focus must be AFTER he FXML is finished loading
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
//				class1Grade.requestFocus();
			}
		});
	}
	public void test(){
//		class1Grade.requestFocus();
		System.out.println("Called test");
		model.calcGpaOverall(classes);
	}

	@FXML
	public void handleClass1Title(ActionEvent event){
		gpaOverallLabel.setText( ((TextInputControl) event.getSource()).getText() );
		System.out.println(event);
		System.out.println(event.getSource() );
		//Text a = new Text
	}
	
	@FXML
	public void handleClass1Grade(ActionEvent event){
		// gpaOverallLabel.setText( ((TextInputControl) event.getSource()).getText() );
		System.out.println("Pressed Enter");
	}

	@FXML
	public void handleClass1Credits(ActionEvent event){
		// gpaOverallLabel.setText( ((TextInputControl) event.getSource()).getText() );
	}

	@FXML
	public void handleCalcGpaOverallButton(ActionEvent event){
		model.calcGpaOverall(classes);
		gpaOverallLabel.setText(numToText( model.getGpaOverall() ));
	}

	@FXML
	public void handleResetButton(ActionEvent event){
		model.reset();
		gpaOverallLabel.setText("0.0");
	}


	//auto-convert numbers to text for setting GUI elements
	public static String numToText(double number){
		return numToText(number +"");
	}
	public static String numToText(String number){
		return number;
	}


	class TextFieldListener implements ChangeListener<Boolean> {
		private final TextField numericTextField;
		private final Label errorLabel;
		private String type;

		TextFieldListener(TextField numericTextField, Label errorLabel) {
			this(numericTextField, errorLabel, "");
		}
		TextFieldListener(TextField numericTextField, Label errorLabel, String type) {
			this.numericTextField = numericTextField;
			this.errorLabel = errorLabel;
			this.type=type;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			if(!newValue){		// check if focus gained or lost
				String errorText = "";
				if(type.equals("currentGPA")){
					errorText=model.getGPAErrorTextIfInvalid(numericTextField.getText());
					errorLabel.setText(errorText);		//Always set error text (clears error if they fixed the mistake)
					if(errorText.equals("")){
						gpaOverallLabel.setText("model.calcGpaOverall()");
					}
				}else{
					//check if INVALID
					errorText=" err";
					// String errorText = model.getNumericErrorTextIfInvalid();
					errorLabel.setText(numericTextField.getText()+errorText);
				}
			}
		}
	}

}