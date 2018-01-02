import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


	public void validateClass(Class classObj){
		classObj.valid=true;		//assume it's valid & check for invalid

		if(isEmptyString(classObj.getCredits())){
			classObj.qualityPointsLabel.setText("Credits can't be empty");
			classObj.valid = false;
		}
		else if(!isNumeric(classObj.getCredits())){
			classObj.qualityPointsLabel.setText("Only numbers allowed");
			classObj.valid = false;
		}
		else{
			double credits = Double.parseDouble(classObj.getCredits());
			 if(credits <= 0){
				classObj.qualityPointsLabel.setText("Credits must be positive");
				classObj.valid = false;
			}
		}
	}

	public void setQualityPoints(Class classObj){
		if(classObj.valid){
			double qualityPoints = Double.parseDouble(classObj.getCredits()) * letterGradeToNumber.get(classObj.getGrade());
			classObj.qualityPointsLabel.setText(GpaController.round(qualityPoints, 2) + "");
		}
		else{
			//change css to be greyed out
		}
	}

	public boolean areAllClassesValid(ArrayList<Class> classes){
		for(Class c : classes){
			if(!c.valid){
				return false;
			}
		}
		return true;
	}

	//No error handling here, other methods check for invalid input and calcGpaOverall() is only called if everything  is valid
	public void calcGpaOverall(ArrayList<Class> classes){
		double totalQualityPoints=0;
		double totalCredits=0;
		for(Class c : classes){
			totalQualityPoints += Double.parseDouble(c.getCredits()) * letterGradeToNumber.get(c.getGrade());
			totalCredits += Double.parseDouble(c.getCredits());
		}
		System.out.println("quality="+totalQualityPoints+"\tcredits="+totalCredits);
		// if(currentGPA not empty && is valis){
		// 	totalQualityPoints += currentGPA;
		// 	totalCredits += currentCredits;
		// }
		gpaOverall = totalQualityPoints / totalCredits;
	}

	public double getGpaOverall(){
		return gpaOverall;
	}

	public void reset(){
		gpaOverall=0;
	}

}