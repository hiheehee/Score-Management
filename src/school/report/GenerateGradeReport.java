package school.report;

import java.util.ArrayList;
import java.util.Collections;

import grade.BasicEvaluation;
import grade.GradeEvaluation;
import grade.MajorEvaluation;
import grade.PassFailEvaluation;
import school.School;
import school.Score;
import school.Student;
import school.Subject;
import utils.Define;

public class GenerateGradeReport {

	School school = School.getInstance(); 
	public static final String TITLE = " 수강생 학점 \t\t\n"; // String.format("%400s\t\\t\\n"," "수강생 학점";
	public static final String HEADER = String.format("%10s%10s|%5s%10s|%6s%6s|%5s%10s\n", "이름","","학번","","중점과목","","점수","|");
	public static final String LINE = "---------------------------------------------\n";
	private StringBuffer buffer = new StringBuffer();  
	
	public String getReport(){
		ArrayList<Subject> subjectList = school.getSubjectList();  // 모든 과목에 대해
		for(Subject subject : subjectList) { // 각 과목 학점을 산출하여 Header, body, footer 출력
			makeHeader(subject);
			makeBody(subject);
			makeFooter();
		}
		return buffer.toString();  // String 으로 반환
	}
	
	public void makeHeader(Subject subject){ // report의 Header영역 출력
		buffer.append(GenerateGradeReport.LINE);
		buffer.append(String.format("%40s", subject.getSubjectName()));
		buffer.append(GenerateGradeReport.TITLE);
		buffer.append(GenerateGradeReport.HEADER);
		buffer.append(GenerateGradeReport.LINE);
	} 
	
	public void makeBody(Subject subject){ // report의 Body영역으로 각 과목의 학생 정보 출력
		ArrayList<Student> studentList = subject.getStudentList();
		Collections.sort(studentList);
		
		for(int i = 0; i < studentList.size(); i++){ // 각 학생별 Name | Id | 중점과목 | 점수
			Student student = studentList.get(i);
			buffer.append(String.format("%10s",student.getStudentName()));
			buffer.append(" | ");
			buffer.append(String.format("%8s",student.getStudentId()));
			buffer.append(" | ");
			buffer.append(String.format("%7s",student.getMajorSubject().getSubjectName() + "\t"));
			buffer.append(" | ");
			
			getScoreGrade(student, subject);  //학생별 해당과목 학점 계산
			buffer.append("\n");
			buffer.append(LINE);
		}
	}
	
	public void getScoreGrade(Student student, Subject subject){	
		ArrayList<Score> scoreList = student.getScoreList();
		int majorId = student.getMajorSubject().getSubjectId();
		
		GradeEvaluation[] gradeEvaluation = {new BasicEvaluation(), new MajorEvaluation(), new PassFailEvaluation()};  //학점 평가 클래스들
		
		for(int i = 0; i < scoreList.size(); i++) { // 학생이 가진 점수들 
			Score score = scoreList.get(i);
			if(score.getSubject().getSubjectId() == subject.getSubjectId()) { // 현재 학점을 산출할 과목 
				String grade;
		
				if(subject.getGradeType() == Define.PF_TYPE) {
					grade = gradeEvaluation[Define.PF_TYPE].getGrade(score.getPoint());
				}else {
				    if(score.getSubject().getSubjectId() == majorId)  // 중점 과목인 경우
					    grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());//중점 과목 학점 평가 방법  
				    else
				    	grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint()); // 중점 과목이 아닌 경우
				}
				buffer.append(String.format("%3s",score.getPoint()));
				buffer.append(" : ");
				buffer.append(String.format("%2s",grade));
				buffer.append(" | ");
			}
		}
	}
	
	public void makeFooter() {
		buffer.append("\n");
	}
}