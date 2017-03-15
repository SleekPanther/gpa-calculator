import javafx.event.*;

public class GpaController {
	private GpaView view;
	private GpaModel model;

	public GpaController(GpaView view, GpaModel model){
		System.out.println("controller constructor");
		this.view=view;
		this.model=model;
		
		view.addCalcButtonHandler(new CalcButtonHandler());
	}
	
	class CalcButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
        	view.setGpaOverall(System.nanoTime());
        }
    }

//	class ButtonHandler2 implements EventHandler<ActionEvent>{
//	//	@Override
//	//	public void handle(Event event) {
//	//		view.setGpaOverall(2);
//	//	}
//
//		@Override
//		public void handle(ActionEvent arg0) {
//			view.setGpaOverall(System.nanoTime());
//		}
//
//	}

}





// class calcOverallGpaButtonListener implements ActionListener{

// 	public void actionPerformed(ActionEvent e) {
// 		// try{
// 		// 	firstNumber = theView.getFirstNumber();
// 		// 	secondNumber = theView.getSecondNumber();

// 		// 	theModel.addTwoNumbers(firstNumber, secondNumber);

// 		// 	theView.setCalcSolution(theModel.getCalculationValue());
// 		// }

// 		// catch(NumberFormatException ex){

// 			System.out.println(ex);

// 			view.setGpaOverall(2);

// 		// }

// 	}

// }
