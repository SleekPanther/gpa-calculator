import javafx.event.*;

public class ButtonHandler implements EventHandler<ActionEvent>{
	private GpaView view;

	public ButtonHandler(GpaView view){
		this.view=view;
	}

//	@Override
//	public void handle(Event event) {
//		view.setGpaOverall(2);
//	}

	@Override
	public void handle(ActionEvent arg0) {
		view.setGpaOverall(System.nanoTime());
	}

}