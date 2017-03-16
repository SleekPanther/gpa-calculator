import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.util.*;

public class GpaView extends Application {
	private int windowHeight = 600;
	private int windowWidth = 500;

	private Stage primaryStage;
	private Scene mainScene;
	private VBox mainPane;
	private HBox classPane;
	private HBox calcPane;
		private Button calcGpaOverallButton;
		private Label gpaOverallLabel;
		private String gpaOverallDefaultText = "0.0";
		private Button resetButton;


	public GpaView(){
		// System.out.println("gpa view constructor");
	}

	@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

	// @Override
	// public void start(Stage stage) throws Exception {
	// 	System.out.println("start");

	// 	Parent rootParent = FXMLLoader.load(getClass().getResource("ui.fxml"));
 //        // primaryStage.setTitle("Hello World");
 //        // primaryStage.setScene(new Scene(root, 300, 275));
 //        // primaryStage.show();


	// 	primaryStage=stage;
	// 	// setUpGui();

	// 	GpaModel model = new GpaModel();
	// 	GpaController controller = new GpaController(this, model);
		
	// 	primaryStage.setScene(new Scene(rootParent, 300, 275));		//what are these dimensions??????????
	// 	primaryStage.show();
	// }

	// public static void main(String[] args) {
	// 	System.out.println("main");
	// 	launch(args);
	// }

	// private void setUpGui(){
	// 	calcPane=new HBox();
	// 	calcGpaOverallButton = new Button("Calculate Gpa");
	// 	gpaOverallLabel = new Label(gpaOverallDefaultText);
	// 	resetButton=new Button("Reset All");
	// 	calcPane.getChildren().addAll(calcGpaOverallButton, gpaOverallLabel, resetButton);

	// 	mainPane = new VBox();
	// 	mainPane.getChildren().addAll(calcPane);

	// 	mainScene = new Scene(mainPane);
		
	// 	primaryStage.setTitle("GPA Calculator");
	// 	primaryStage.setScene(mainScene);
	// 	primaryStage.setHeight(windowHeight);
	// 	primaryStage.setWidth(windowWidth);
	// 	primaryStage.setResizable(false);
	// }
	
	// public void addCalcButtonHandler(EventHandler<ActionEvent> calcButtonHandler){
	// 	calcGpaOverallButton.setOnAction(calcButtonHandler);
	// }

	// public void addResetButtonHandler(EventHandler<ActionEvent> resetButtonHandler){
	// 	resetButton.setOnAction(resetButtonHandler);
	// }


	// public void setGpaOverall(String gpa){
	// 	gpaOverallLabel.setText(gpa);
	// }

	// //set text passing a number
	// public void setGpaOverall(double gpa){
	// 	setGpaOverall(gpa+"");		//convert to string
	// }

}