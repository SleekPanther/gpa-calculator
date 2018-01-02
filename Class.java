import javafx.scene.control.*;

public class Class{
	public TextField title;
	public ComboBox<String> grade;
	public TextField credits;
	public Label qualityPointsLabel;
	
	public Class(TextField title, ComboBox<String> grade, TextField credits, Label qualityPointsLabel){
		this.title=title;
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