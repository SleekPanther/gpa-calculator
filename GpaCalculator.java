import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.*;

public class GpaCalculator extends Application {
	private static final String guiFxmlFileName = "ui.fxml";
	// private int windowHeight = 600;
	// private int windowWidth = 500;

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource(guiFxmlFileName));
		primaryStage.setTitle("GPA Calculator");
		primaryStage.setScene(new Scene(root));
		// primaryStage.setScene(mainScene);
		// primaryStage.setHeight(windowHeight);
		// primaryStage.setWidth(windowWidth);
		// primaryStage.setResizable(false);
		
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {	//Destroy on key PRESS to avoid calling any other methods when closing
			if (KeyCode.ESCAPE == event.getCode()) {
				primaryStage.close();
			}
		});
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}