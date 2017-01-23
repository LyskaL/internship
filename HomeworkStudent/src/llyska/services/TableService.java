package llyska.services;

import java.util.List;

import llyska.entities.Student;
import llyska.interfaces.TableView;

public interface TableService {

    TableView getTableView();
    List<Student> getGroup();
    void cleanTable();

    void addStudent(Student student);
    void removeStudent(Student student);
    void removeStudent(int index);
    void setStudent(Student student, int index);

}
