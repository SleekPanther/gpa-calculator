import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class GpaModel {
	private DecimalFormat numberFormatter = new DecimalFormat("0.00");

	private final int GPA_UPPER_BOUND = 4;
	
	private HashMap<String, Double> letterGradeToNumber = new HashMap<String, Double>();

	private double gpaOverall;


	public GpaModel() {
		letterGradeToNumber.put("A+", 4.0);
		letterGradeToNumber.put("A", 4.0);
		letterGradeToNumber.put("A-", 3.67);
		letterGradeToNumber.put("B+", 3.33);
		letterGradeToNumber.put("B", 3.0);
		letterGradeToNumber.put("B-", 2.67);
		letterGradeToNumber.put("C+", 2.33);
		letterGradeToNumber.put("C", 2.0);
		letterGradeToNumber.put("C-", 1.67);
		letterGradeToNumber.put("D+", 1.33);
		letterGradeToNumber.put("D", 1.0);
		letterGradeToNumber.put("F", 0.0);
	}

	public String getGPAErrorIfInvalid(String number){
		String errorText="";
		if(Double.parseDouble(number) > GPA_UPPER_BOUND){
			errorText="Error: GPA can't be > 4.0";
		}
		return errorText;
	}

	public static boolean isNumeric(String number){
		try{
			Double.parseDouble(number);
		}
		catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	public static boolean isEmptyString(String input){
		return input.trim().equals("");
	}


	public boolean isClassValid(Class classObj){
		if(isEmptyString(classObj.credits.getText())){
			return false;
		}
		return true;
	}

	public void setQualityPoints(Class classObj){
		double qualityPoints = Integer.parseInt(classObj.credits.getText()) * letterGradeToNumber.get(classObj.grade.getValue());
		classObj.qualityPointsLabel.setText(numberFormatter.format(GpaController.round(qualityPoints, 2)));
	}

	public void calcGpaOverall(ArrayList<Class> classes, TextField currentGPA, TextField currentCredits) {
		double totalQualityPoints=0;
		int totalCredits=0;
		for(Class c : classes){
			//Ignore invalid classes from the calculation
			if(isClassValid(c)){
				totalQualityPoints += Double.parseDouble(c.credits.getText()) * letterGradeToNumber.get(c.grade.getValue());
				totalCredits += Integer.parseInt(c.credits.getText());
			}
		}
		if(!isEmptyString(currentGPA.getText()) && !isEmptyString(currentCredits.getText()) && getGPAErrorIfInvalid(currentGPA.getText()).equals("")){
			totalQualityPoints += Double.parseDouble(currentGPA.getText()) * Integer.parseInt(currentCredits.getText());
			totalCredits += Integer.parseInt(currentCredits.getText());
		}

		if(totalCredits==0){
			gpaOverall=0;
		}else{
			gpaOverall = totalQualityPoints / totalCredits;
		}
	}

	public double getGpaOverall(){
		return gpaOverall;
	}

	public void reset(){
		gpaOverall=0;
	}

}