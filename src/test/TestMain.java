package test;

import school.School;
import school.Score;
import school.Student;
import school.Subject;
import school.report.GenerateGradeReport;
import utils.Define;

public class TestMain {

	School happySchool = School.getInstance(); 
	Subject korean; // ����
	Subject math; // ����
	Subject english; // ����
	
	GenerateGradeReport gradeReport = new GenerateGradeReport(); // ���
	
	public static void main(String[] args) {
		TestMain test = new TestMain();
		
		test.creatSubject();
		test.createStudent();
		
		String report = test.gradeReport.getReport(); //���� ��� ����
		System.out.println(report); // ���
		
	}
	
	//�׽�Ʈ ���� ����
	public void creatSubject(){
		korean = new Subject("����", Define.KOREAN);
		math = new Subject("����", Define.MATH);
		english = new Subject("�⺻����", Define.ENGLISH);
		
		english.setGradeType(Define.PF_TYPE);
		
		happySchool.addSubject(korean);
		happySchool.addSubject(math);
		happySchool.addSubject(english);
		
	}
	
	//�׽�Ʈ �л� ����
	public void createStudent(){
		Student student1 = new Student(150302, "Tomas", korean);
		Student student2 = new Student(150408, "Jenny", math);
		Student student3 = new Student(170201, "Eavan", korean);
		Student student4 = new Student(202001, "Scarlet", korean);
		Student student5 = new Student(202025, "Katie", math);
		
		// �б��� �л� ���
		happySchool.addStudent(student1);
		happySchool.addStudent(student2);
		happySchool.addStudent(student3);
		happySchool.addStudent(student4);
		happySchool.addStudent(student5);

		// ���� ���� ������û
		korean.register(student1);
		korean.register(student2);
		korean.register(student3);
		korean.register(student4);
		korean.register(student5);
		
		// ���� ���� ���� ���
		addScoreForStudent(student1, korean, 92); 
		addScoreForStudent(student2, korean, 100);
		addScoreForStudent(student3, korean, 100);
		addScoreForStudent(student4, korean, 89); 
		addScoreForStudent(student5, korean, 65); 
		
		// ���� ���� ������û
		math.register(student1);
		math.register(student2);
		math.register(student3);
		math.register(student4);
		math.register(student5);
		
		// ���а��� ���� ���
		addScoreForStudent(student1, math, 100);
		addScoreForStudent(student2, math, 100);
		addScoreForStudent(student3, math, 78);
		addScoreForStudent(student4, math, 95);	
		addScoreForStudent(student5, math, 56);
		
		// ���� ���� ������û
		english.register(student1);
		english.register(student2);
		english.register(student3);
		
		// ���� ���� ���� ���
		addScoreForStudent(student1, english, 95);	
		addScoreForStudent(student2, english, 85); 
		addScoreForStudent(student3, english, 55);	
		
	}

	//���� ���� �Է�
	public void addScoreForStudent(Student student, Subject subject, int point){
		Score score = new Score(student.getStudentId(), subject, point);
		student.addSubjectScore(score);
	}
}