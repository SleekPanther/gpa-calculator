import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GpaView extends Application {
	private VBox pane;
	
	public GpaView(){
		System.out.println("gpa view constructor");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		setUpGui();
		
		GpaModel model = new GpaModel();
		GpaController controller = new GpaController(this, model);
		
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}

	public static void main(String[] args) {
		System.out.println("main");
		launch(args);
	}

	public void setUpGui(){
		pane = new VBox();
		
	}

}