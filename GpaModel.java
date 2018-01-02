import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextField;

public class GpaModel {
	private final double GPA_UPPER_BOUND = 4;
	private final double GPA_LOWER_BOUND = 0;
	private final double CREDITS_LOWER_BOUND = 0;
	
	private HashMap<String, Double> letterGradeToNumber = new HashMap<String, Double>();

	private double gpaOverall;

	public GpaModel() {
		letterGradeToNumber.put("A+", 4.0);
		letterGradeToNumber.put("A", 4.0);
		letterGradeToNumber.put("A-", 11/3.0);
		letterGradeToNumber.put("B+", 10/3.0);
		letterGradeToNumber.put("B", 3.0);
		letterGradeToNumber.put("B-", 8/3.0);
		letterGradeToNumber.put("C+", 7/3.0);
		letterGradeToNumber.put("C", 2.0);
		letterGradeToNumber.put("C-", 5/3.0);
		letterGradeToNumber.put("D+", 4/3.0);
		letterGradeToNumber.put("D", 1.0);
		letterGradeToNumber.put("F", 0.0);
	}

	public String getGPAErrorIfInvalid(String number){
		String errorText="";

		if(!isNumeric(number)){
			if(isEmptyString(number)){		//Return early if TextBox is empty (ignore since optional calculation)
				return "";
			}
			errorText = "Error: GPA must be a number";
		}
		else{
			double gpa;
			gpa = Double.parseDouble(number);
			if(gpa > GPA_UPPER_BOUND){
				errorText="Error: GPA can't be > 4.0";
			}
			if(gpa < GPA_LOWER_BOUND){
				errorText="Error: GPA can't be negative";
			}
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
		if(isEmptyString(classObj.getCredits())){
			return false;
		}
		return true;
	}

	public void setQualityPoints(Class classObj){
		double qualityPoints = Double.parseDouble(classObj.getCredits()) * letterGradeToNumber.get(classObj.getGrade());
		classObj.qualityPointsLabel.setText(GpaController.round(qualityPoints, 2) + "");
	}

	public void calcGpaOverall(ArrayList<Class> classes, TextField currentGPA, TextField currentCredits) {
		double totalQualityPoints=0;
		int totalCredits=0;
		for(Class c : classes){
			//Ignore invalid classes from the calculation
			if(isClassValid(c)){
				totalQualityPoints += Double.parseDouble(c.getCredits()) * letterGradeToNumber.get(c.getGrade());
				totalCredits += Integer.parseInt(c.getCredits());
			}
		}
		if(!isEmptyString(currentGPA.getText()) && !isEmptyString(currentCredits.getText())){
			totalQualityPoints += Double.parseDouble(currentGPA.getText()) * Integer.parseInt(currentCredits.getText());
			totalCredits += Integer.parseInt(currentCredits.getText());
		}
		gpaOverall = totalQualityPoints / totalCredits;
	}

	public double getGpaOverall(){
		return gpaOverall;
	}

	public void reset(){
		gpaOverall=0;
	}


}