import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.stage.*;

public class GpaCalculator extends Application {
	private static final String guiFxmlFileName = "view.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource(guiFxmlFileName));
		primaryStage.setTitle("GPA Calculator");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root));

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