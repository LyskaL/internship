package llyska.services;

import java.util.Collections;
import java.util.List;

import llyska.entities.Group;
import llyska.entities.Student;

public class TableServiceImp implements TableService {
    private final Group _group;

    public TableServiceImp() {
        _group = new Group();
    }

    @Override
    public void cleanStudents() {

    }

    @Override
    public void addStudent(Student student) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeStudent(Student student) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeStudent(int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setStudent(Student student, int index) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Student> getGroup() {
        return Collections.unmodifiableList(_group.getGroup());
    }

}
