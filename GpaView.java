import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.event.ActionListener;
import java.util.*;

public class GpaView extends Application {
	private int windowHeight = 500;
	private int windowWidth = 300;

	private Stage primaryStage;
	private Scene mainScene;
	private VBox mainPane;
	private HBox classPane;
	private HBox calcPane;
		private Button calcGpaOverallButton;
		private Label gpaOverallLabel;


	public GpaView(){
		// System.out.println("gpa view constructor");
	}

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("start");
		primaryStage=stage;
		setUpGui();
		
		GpaModel model = new GpaModel();
		GpaController controller = new GpaController(this, model);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		System.out.println("main");
		launch(args);
	}

	private void setUpGui(){
		calcPane=new HBox();
		calcGpaOverallButton = new Button("Calculate Gpa");
		gpaOverallLabel = new Label("00");
		calcPane.getChildren().addAll(calcGpaOverallButton, gpaOverallLabel);

		mainPane = new VBox();
		mainPane.getChildren().addAll(calcPane);

		mainScene = new Scene(mainPane);
		
		primaryStage.setTitle("GPA Calculator");
		primaryStage.setScene(mainScene);
		primaryStage.setHeight(windowHeight);
		primaryStage.setWidth(windowWidth);
		primaryStage.setResizable(false);

		//createButtonHandlers();
	}
	
	public void addCalcButtonHandler(EventHandler<ActionEvent> buttonHandler){
		//calcGpaOverallButton.addActionListener(buttonListener);
		//calcGpaOverallButton.addEventHandler(arg0, arg1);
		calcGpaOverallButton.setOnAction(buttonHandler);
		//calcGpaOverallButton
	}

	private void createButtonHandlers2(){
		//calcGpaOverallButton.addActionListener(new ButtonHandler2(this)) ;
		// calcGpaOverallButton.setOnAction(new ButtonHandler(this)) ;

		// calcGpaOverallButton.setOnAction(new EventHandler<ActionEvent>() {
		//     @Override public void handle(ActionEvent e) {
		//     	setGpaOverall("Changed");
		//     }
		// });
	}

	public void setGpaOverall(String gpa){
		gpaOverallLabel.setText(gpa);
	}

	//set text passing a number
	public void setGpaOverall(double gpa){
		setGpaOverall(gpa+"");
	}

}