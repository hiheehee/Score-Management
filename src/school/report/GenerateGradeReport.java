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
	public static final String TITLE = " ������ ���� \t\t\n"; // String.format("%400s\t\\t\\n"," "������ ����";
	public static final String HEADER = String.format("%10s%10s|%5s%10s|%6s%6s|%5s%10s\n", "�̸�","","�й�","","��������","","����","|");
	public static final String LINE = "---------------------------------------------\n";
	private StringBuffer buffer = new StringBuffer();  
	
	public String getReport(){
		ArrayList<Subject> subjectList = school.getSubjectList();  // ��� ���� ����
		for(Subject subject : subjectList) { // �� ���� ������ �����Ͽ� Header, body, footer ���
			makeHeader(subject);
			makeBody(subject);
			makeFooter();
		}
		return buffer.toString();  // String ���� ��ȯ
	}
	
	public void makeHeader(Subject subject){ // report�� Header���� ���
		buffer.append(GenerateGradeReport.LINE);
		buffer.append(String.format("%40s", subject.getSubjectName()));
		buffer.append(GenerateGradeReport.TITLE);
		buffer.append(GenerateGradeReport.HEADER);
		buffer.append(GenerateGradeReport.LINE);
	} 
	
	public void makeBody(Subject subject){ // report�� Body�������� �� ������ �л� ���� ���
		ArrayList<Student> studentList = subject.getStudentList();
		Collections.sort(studentList);
		
		for(int i = 0; i < studentList.size(); i++){ // �� �л��� Name | Id | �������� | ����
			Student student = studentList.get(i);
			buffer.append(String.format("%10s",student.getStudentName()));
			buffer.append(" | ");
			buffer.append(String.format("%8s",student.getStudentId()));
			buffer.append(" | ");
			buffer.append(String.format("%7s",student.getMajorSubject().getSubjectName() + "\t"));
			buffer.append(" | ");
			
			getScoreGrade(student, subject);  //�л��� �ش���� ���� ���
			buffer.append("\n");
			buffer.append(LINE);
		}
	}
	
	public void getScoreGrade(Student student, Subject subject){	
		ArrayList<Score> scoreList = student.getScoreList();
		int majorId = student.getMajorSubject().getSubjectId();
		
		GradeEvaluation[] gradeEvaluation = {new BasicEvaluation(), new MajorEvaluation(), new PassFailEvaluation()};  //���� �� Ŭ������
		
		for(int i = 0; i < scoreList.size(); i++) { // �л��� ���� ������ 
			Score score = scoreList.get(i);
			if(score.getSubject().getSubjectId() == subject.getSubjectId()) { // ���� ������ ������ ���� 
				String grade;
		
				if(subject.getGradeType() == Define.PF_TYPE) {
					grade = gradeEvaluation[Define.PF_TYPE].getGrade(score.getPoint());
				}else {
				    if(score.getSubject().getSubjectId() == majorId)  // ���� ������ ���
					    grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());//���� ���� ���� �� ���  
				    else
				    	grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint()); // ���� ������ �ƴ� ���
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