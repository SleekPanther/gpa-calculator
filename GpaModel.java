
public class GpaModel {
	private final double GPA_UPPER_BOUND = 4;
	private final double GPA_LOWER_BOUND = 0;
	private final double CREDITS_LOWER_BOUND = 0;
	

	private double gpaOverall;
	//list of classes?

	public GpaModel() {

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


	public void calcGpaOverall(){	//helper methods throw exceptions
		double total=0;
		total=11;
		//for(){} all classes passed in
		gpaOverall=total/5;
		
		gpaOverall=System.nanoTime();
	}

	public double getGpaOverall(){
		return gpaOverall;
	}

	public void reset(){

	}

}