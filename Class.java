import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Class{
	private ComboBox<String> grade;
	private TextField credits;
	
	public Class(ComboBox<String> grade, TextField credits){
		this.grade=grade;
		this.credits=credits;
	}
	
	public String getGrade(){
		return grade.getValue();
	}
	
	public String getCredits(){
		return credits.getText();
	}
}