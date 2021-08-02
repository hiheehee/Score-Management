package test;

import school.School;
import school.Score;
import school.Student;
import school.Subject;
import school.report.GenerateGradeReport;
import utils.Define;

public class TestMain {

	School happySchool = School.getInstance(); 
	Subject korean; // 국어
	Subject math; // 수학
	Subject english; // 영어
	
	GenerateGradeReport gradeReport = new GenerateGradeReport(); // 출력
	
	public static void main(String[] args) {
		TestMain test = new TestMain();
		
		test.creatSubject();
		test.createStudent();
		
		String report = test.gradeReport.getReport(); //성적 결과 생성
		System.out.println(report); // 출력
		
	}
	
	//테스트 과목 생성
	public void creatSubject(){
		korean = new Subject("국어", Define.KOREAN);
		math = new Subject("수학", Define.MATH);
		english = new Subject("기본영어", Define.ENGLISH);
		
		english.setGradeType(Define.PF_TYPE);
		
		happySchool.addSubject(korean);
		happySchool.addSubject(math);
		happySchool.addSubject(english);
		
	}
	
	//테스트 학생 생성
	public void createStudent(){
		Student student1 = new Student(150302, "Tomas", korean);
		Student student2 = new Student(150408, "Jenny", math);
		Student student3 = new Student(170201, "Eavan", korean);
		Student student4 = new Student(202001, "Scarlet", korean);
		Student student5 = new Student(202025, "Katie", math);
		
		// 학교에 학생 등록
		happySchool.addStudent(student1);
		happySchool.addStudent(student2);
		happySchool.addStudent(student3);
		happySchool.addStudent(student4);
		happySchool.addStudent(student5);

		// 국어 과목 수강신청
		korean.register(student1);
		korean.register(student2);
		korean.register(student3);
		korean.register(student4);
		korean.register(student5);
		
		// 국어 과목 성적 등록
		addScoreForStudent(student1, korean, 92); 
		addScoreForStudent(student2, korean, 100);
		addScoreForStudent(student3, korean, 100);
		addScoreForStudent(student4, korean, 89); 
		addScoreForStudent(student5, korean, 65); 
		
		// 수학 과목 수강신청
		math.register(student1);
		math.register(student2);
		math.register(student3);
		math.register(student4);
		math.register(student5);
		
		// 수학과목 성적 등록
		addScoreForStudent(student1, math, 100);
		addScoreForStudent(student2, math, 100);
		addScoreForStudent(student3, math, 78);
		addScoreForStudent(student4, math, 95);	
		addScoreForStudent(student5, math, 56);
		
		// 영어 과목 수강신청
		english.register(student1);
		english.register(student2);
		english.register(student3);
		
		// 영어 과목 성적 등록
		addScoreForStudent(student1, english, 95);	
		addScoreForStudent(student2, english, 85); 
		addScoreForStudent(student3, english, 55);	
		
	}

	//과목별 성적 입력
	public void addScoreForStudent(Student student, Subject subject, int point){
		Score score = new Score(student.getStudentId(), subject, point);
		student.addSubjectScore(score);
	}
}