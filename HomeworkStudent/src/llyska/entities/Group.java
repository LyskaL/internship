package llyska.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private final List<Student> _students;

    public Group() {
        _students = new ArrayList<>();

        _students.add(new Student("Lyuda", "1", true));
        _students.add(new Student("Max", "3", true));
        _students.add(new Student("Adam", "1", false));
        _students.add(new Student("Maria", "1", true));
        _students.add(new Student("Denis", "3", true));
        _students.add(new Student("Alex", "1", false));
        _students.add(new Student("Irina", "1", true));
    }

    public void add(Student student) {
        if (student != null) {
            _students.add(student);
        }
    }

    public void remove(Student student) {
        if (student != null) {
            _students.remove(student);
        }
    }

    public void remove(int index) {
        if (index < size() && index >= 0) {
            _students.remove(index);
        }
    }

    public void clean() {
        _students.clear();
    }

    public void set(Student student, int index) {
        if (index < size() && index >= 0) {
            _students.set(index, student);
        }
    }

    public int size() {
        return _students.size();
    }

    public Student getStudent(int index) {
        return (index < size() && index >= 0) ? _students.get(index) : null;
    }

    public List<Student> getGroup() {
        return Collections.unmodifiableList(_students);
    }

    public int getIndex(Student student) {
        return _students.indexOf(student);
    }
}
