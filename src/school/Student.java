package school;

import java.util.ArrayList;

public class Student implements Comparable<Student>{
	
	private int studentId; // 학번
	private String studentName; // 이름 
	private Subject majorSubject; // 전공
	private ArrayList<Score> scoreList = new ArrayList<>(); // 성적 리스트
	
	public Student(int studentId, String studentName, Subject majorSubject) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.majorSubject = majorSubject;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Subject getMajorSubject() {
		return majorSubject;
	}

	public void setMajorSubject(Subject majorSubject) {
		this.majorSubject = majorSubject;
	}

	public ArrayList<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(ArrayList<Score> scoreList) {
		this.scoreList = scoreList;
	}
	
	// 과목 성적 등록
	public void addSubjectScore(Score score) {
		scoreList.add(score);
	}

	@Override
	public int compareTo(Student o) { // 학생 이름순 정렬
		return this.studentName.compareTo(o.studentName);
	}

}