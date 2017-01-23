package llyska.jface.tableexample;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final List<Student> _students;
    private int _numberGroup;

    public Group(int numberGroup) {
        _students = new ArrayList<>();
        _numberGroup = numberGroup;

        addStudents(new Student("Lyuda", false));
        addStudents(new Student("Mihail", true));
        addStudents(new Student("Denis", false));
        addStudents(new Student("Max", true));
        addStudents(new Student("Maria", false));

        // TODO Validator
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
        return _numberGroup;
    }

    public void setNumberGroup(int numberGroup) {
        this._numberGroup = numberGroup;
    }

    public int size() {
        return _students.size();
    }

}
