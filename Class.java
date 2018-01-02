import javafx.scene.control.*;

public class Class{
	public ComboBox<String> grade;
	public TextField credits;
	public Label qualityPointsLabel;
	public boolean valid;	//is the class in a valid state (e.g. has both credits and grade & valid values)
	
	public Class(ComboBox<String> grade, TextField credits, Label qualityPointsLabel){
		this.grade=grade;
		this.credits=credits;
		this.qualityPointsLabel=qualityPointsLabel;
		valid=false;		//initialize false because credits TextField is empty to start
	}
	
	public String getGrade(){
		return grade.getValue();
	}
	
	public String getCredits(){
		return credits.getText();
	}
}