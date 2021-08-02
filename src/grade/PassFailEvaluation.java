package grade;

public class PassFailEvaluation implements GradeEvaluation{

	@Override
	public String getGrade(int point) {
		if (60 <= point)   
			return "P";
		else
		    return "F";
	}
}