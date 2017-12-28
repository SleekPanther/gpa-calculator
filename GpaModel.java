import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GpaModel {
	private final double GPA_UPPER_BOUND = 4;
	private final double GPA_LOWER_BOUND = 0;
	private final double CREDITS_LOWER_BOUND = 0;
	
	HashMap<String, Double> letterGradeToNumber = new HashMap<String, Double>();

	private double gpaOverall;
	//list of classes?

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

	public String getGPAErrorTextIfInvalid(String number){
		String errorText="";

		double gpa;
		if(!isNumeric(number)){
			errorText = "Error: GPA must be a number";
		}
		else{
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


	public void calcGpaOverall(ArrayList<Class> classes){	//helper methods throw exceptions
		double total=0;
		total=11;
		//for(){} all classes passed in
		gpaOverall=total/5;
		
		gpaOverall=System.nanoTime();
		
		for(Class c : classes){
			System.out.println(c.getGrade() + " " + c.getCredits());
		}
	}

	public double getGpaOverall(){
		return gpaOverall;
	}

	public void reset(){

	}

}