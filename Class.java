import javafx.scene.control.*;

public class Class{
	public ComboBox<String> grade;
	public TextField credits;
	public Label qualityPointsLabel;
	
	public Class(ComboBox<String> grade, TextField credits, Label qualityPointsLabel){
		this.grade=grade;
		this.credits=credits;
		this.qualityPointsLabel=qualityPointsLabel;
	}
	
	public String getGrade(){
		return grade.getValue();
	}
	
	public String getCredits(){
		return credits.getText();
	}
}