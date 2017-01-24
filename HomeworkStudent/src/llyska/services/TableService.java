package llyska.services;

import java.util.List;

import llyska.entities.Student;

public interface TableService {

    List<Student> getGroup();
    void cleanStudents();

    void addStudent(Student student);
    void removeStudent(Student student);
    void removeStudent(int index);
    void setStudent(Student student, int index);

}
