package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TestMain test = new TestMain();
		
		test.creatSubject();
		test.createTestStudent();
		
		System.out.print("추가하고 싶은 학생이 있습니까? (T/F) : ");
		String str = br.readLine().toLowerCase();
		while(str.equals("t")) {
			System.out.print("학생의 학번을 입력하세요 : ");
			int id = Integer.parseInt(br.readLine());
			System.out.print("학생의 이름을 입력하세요 : ");
			String name = br.readLine();
			System.out.print("학생의 주전공을 입력하세요 : ");
			String major = br.readLine().toLowerCase();
			System.out.print("학생의 국어 점수를 입력하세요 : ");
			int ks = Integer.parseInt(br.readLine());
			System.out.print("학생의 수학 점수을 입력하세요 : ");
			int ms = Integer.parseInt(br.readLine());
			System.out.print("해당 학생은 기본영어를 수강습니까?(T/F) : ");
			String check = br.readLine().toLowerCase();
			int es = -1;
			if (check.equals("t")) {
				System.out.print("학생의 영어 점수을 입력하세요 : ");
				es = Integer.parseInt(br.readLine());
			}
			
			test.createStudent(id, name, major, ks, ms, es);

			System.out.print("추가하고 싶은 학생이 있습니까? (T/F) : ");
			str = br.readLine().toLowerCase();
		}
		
		String report = test.gradeReport.getReport(); //성적 결과 생성
		System.out.println(report); // 출력
		
	}
	
	public void createStudent(int id, String name, String major, int ks, int ms, int es){		
		Subject maj = korean;;
		if(major.equals("math")) maj = math;
		
		Student student = new Student(id, name, maj);
		
		happySchool.addStudent(student); // 학교에 학생 등록
		
		korean.register(student); // 국어 과목 수강신청	
		addScoreForStudent(student, korean, ks); // 국어 과목 성적 등록 
		
		math.register(student); // 수학 과목 수강신청
		addScoreForStudent(student, math, ms); // 수학과목 성적 등록
		
		if(-1 < es) {
			english.register(student); // 영어 과목 수강신청
			addScoreForStudent(student, english, es); // 영어 과목 성적 등록
		}
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
	public void createTestStudent(){
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