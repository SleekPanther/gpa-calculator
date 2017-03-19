import javafx.fxml.Initializable;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import java.util.*;

public class GpaController {
	private GpaModel model = new GpaModel();

	//don't need panes??
	// @FXML private VBox mainPane;
	// 	@FXML private VBox classesPane;
		@FXML private TextField class1Gpa;
	// 	@FXML private VBox calcPane;
			@FXML private Button calcGpaOverallButton;
			@FXML private Label gpaOverallLabel;
			@FXML private Button resetButton;

	@FXML
	public void handleClass1Gpa(ActionEvent event){
		gpaOverallLabel.setText( class1Gpa.getText() );
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