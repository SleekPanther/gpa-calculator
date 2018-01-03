import javafx.scene.control.*;

public class Class{
	public int id;
	public TextField title;
	public ComboBox<String> grade;
	public TextField credits;
	public Label qualityPointsLabel;
	public Button removeButton;
	
	public Class(int id, TextField title, ComboBox<String> grade, TextField credits, Label qualityPointsLabel, Button removeButton){
		this.id=id;
		this.title=title;
		this.grade=grade;
		this.credits=credits;
		this.qualityPointsLabel=qualityPointsLabel;
		this.removeButton=removeButton;
	}
	
	public String getGrade(){
		return grade.getValue();
	}
	
	public String getCredits(){
		return credits.getText();
	}
}