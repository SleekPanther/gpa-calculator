import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import javafx.event.*;

public class GpaController  {
	private GpaView view;
	private GpaModel model;

	@FXML private Button button1;

	@FXML
	public void handleBtn1(ActionEvent event){
		button1.setText(System.nanoTime()+"");
	}

	// public GpaController(GpaView view, GpaModel model){
	// 	System.out.println("controller constructor");
	// 	this.view=view;
	// 	this.model=model;
		
	// 	// addViewEventHandlers();
	// }


	// private void addViewEventHandlers(){
	// 	view.addCalcButtonHandler(new CalcButtonHandler());
	// 	view.addResetButtonHandler(new ResetButtonHandler());
	// }


	// class CalcButtonHandler implements EventHandler<ActionEvent> {
	// 	@Override
	// 	public void handle(ActionEvent e) {
	// 		model.calcGpaOverall();
	// 		view.setGpaOverall(model.getGpaOverall());
	// 	}

	// 	//do more complex calutations in method? to extract try/catch
	// }

	// class ResetButtonHandler implements EventHandler<ActionEvent> {
	// 	@Override
	// 	public void handle(ActionEvent e) {
	// 		view.setGpaOverall("0.0");
	// 	}
	// }

}