import javafx.event.*;

public class GpaController {
	private GpaView view;
	private GpaModel model;

	public GpaController(GpaView view, GpaModel model){
		System.out.println("controller constructor");
		this.view=view;
		this.model=model;
		
		addViewEventHandlers();
	}

	private void addViewEventHandlers(){
		view.addCalcButtonHandler(new CalcButtonHandler());
		view.addResetButtonHandler(new ResetButtonHandler());
	}


	class CalcButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			model.calcGpaOverall();
			view.setGpaOverall(model.getGpaOverall());
		}

		//do more complex calutations in method? to extract try/catch
	}

	class ResetButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			view.setGpaOverall("0.0");
		}
	}

}