import java.net.URL;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import java.util.*;

public class GpaController implements Initializable {
	private GpaModel model = new GpaModel();

	//don't need panes??
	// @FXML private VBox mainPane;
	// 	@FXML private GridPane classesPane;
		@FXML private TextField class1Title;
		@FXML private Label class1TitleError;
		@FXML private TextField class1Grade;
		@FXML private Label class1GradeError;
	// 	@FXML private VBox calcPane;
			@FXML private Button calcGpaOverallButton;
			@FXML private Label gpaOverallLabel;
			@FXML private Button resetButton;
	//

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		class1Grade.textProperty().addListener((observable, oldValue, newValue) -> {
			gpaOverallLabel.setText( class1Grade.getText() );
		});

		class1Grade.focusedProperty().addListener(new TextFieldListener(class1Grade, class1GradeError));

		//Request focuse must be AFTER he FXML is finished loading
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				class1Grade.requestFocus();
				
			}
		});
	}
	public void test(){
		class1Grade.requestFocus();
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
		gpaOverallLabel.setText( ((TextInputControl) event.getSource()).getText() );
	}

	@FXML
	public void handleCalcGpaOverallButton(ActionEvent event){
		model.calcGpaOverall();	//pass in classes to calc
		gpaOverallLabel.setText(numToText( model.getGpaOverall() ));
	}

	@FXML
	public void handleResetButton(ActionEvent event){
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

		TextFieldListener(TextField numericTextField, Label errorLabel) {
			this.numericTextField = numericTextField;
			this.errorLabel = errorLabel;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			if(!newValue){	// check if focus gained or lost
				//check if INVALID
				String errorText = "err text";
				errorLabel.setText(errorText);
				// this.numericTextField.setText(getFormattedText(this.numericTextField.getText());
			}
		}
		// public String getFormatedText(String str) {
		// 	return str;
		// }
	}

}