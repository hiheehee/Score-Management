package school;

import java.util.ArrayList;

public class School {

	private static School instance = new School();

	private static String SCHOOL_NAME = "Happy School";
	private ArrayList<Student> studentList = new ArrayList<>(); // 학생 정보
	private ArrayList<Subject> subjectList = new ArrayList<>(); // 과목 정보
	
	private School() {} // 기본 생성자
	
	public static School getInstance() { // 
		if(instance == null) instance = new School();
		return instance;
	}
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public void addStudent(Student student) { // 학생 등록
		studentList.add(student); 
	}

	public void addSubject(Subject subject) { // 과목 등록
		subjectList.add(subject);
	}

	public ArrayList<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(ArrayList<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	
}