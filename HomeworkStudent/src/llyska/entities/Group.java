package llyska.entities;

import java.util.List;

public class Group {
	private List<Student> _students;
	private int numberGroup;
	
	
	public Group(Student student, int numberGroup) {
		//TODO Validator
		addStudents(student);
		setNumberGroup(numberGroup);
	}

	public Student getStudent(int index) {
		return _students.get(index);
	}


	public void addStudents(Student students) {
		_students.add(students);
	}
	
	public void removeStudents(Student students) {
		_students.remove(students);
	}
	
	public void removeStudents(int index) {
		_students.remove(index);
	}

	public int getNumberGroup() {
		return numberGroup;
	}

	public void setNumberGroup(int numberGroup) {
		this.numberGroup = numberGroup;
	}
	
}
