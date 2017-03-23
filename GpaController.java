import java.net.URL;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.util.*;

public class GpaController implements Initializable {
	private GpaModel model = new GpaModel();

	//don't need panes??
	// @FXML private VBox mainPane;
	// 	@FXML private GridPane classesPane;
		@FXML private TextField class1Grade;
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
	}

	@FXML
	public void handleClass1Grade(ActionEvent event){
		gpaOverallLabel.setText( class1Grade.getText() );
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

}