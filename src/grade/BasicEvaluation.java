package grade;

public class BasicEvaluation implements GradeEvaluation {

	@Override
	public String getGrade(int point) {
		String grade;  
		
		if(95 <= point && point <= 100)
			grade = "A+";
		else if(90 <= point && point < 95)
			grade = "A";
		else if(85 <= point && point < 90)
			grade = "B+";
		else if(80 <= point && point < 85)
			grade = "B";
		else if(75 <= point && point < 80)
			grade = "C+";
		else if(70 <= point && point < 75)
			grade = "C";
		else if(55 <= point && point < 70)
			grade = "D";
		else
			grade = "F";
		return grade;
	}

}