

public class GpaModel {
	private double gpaUpperBound = 4;
	private double gpaLowerBound = 0;
	//more limits on valid inputs: grades, 0-100 etc


	private double gpaOverall;
	//classes?

	public GpaModel() {
		System.out.println("model constructor");
	}

	public void calcGpaOverall(){
		double total=0;
		total=11;
		//for(){} all classes passed in
		gpaOverall=total/5;
	}

	public double getGpaOverall(){
		return gpaOverall;
	}

}