package llyska.services;

import java.util.List;

import llyska.entities.Student;
import llyska.events.table.TableEventGenerator;

public interface TableService extends TableEventGenerator {

    List<Student> getGroup();

    void cleanStudents();

    void addStudent(Student student);

    void removeStudent(Student student);

    void removeSelectStudent();

    void setStudent(Student student, int index);

    int getIndex(Student student);

    void setIndexSelect(int index);

    int getIndexSelect();
}
