package llyska.services;

import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import llyska.entities.Group;
import llyska.entities.Student;
import llyska.interfaces.TableView;

public class TableServiceImp implements TableService {
    private final TableView _table;
    private final Group _group;

    public TableServiceImp(Composite parentForTable) {
        _table = new TableView(parentForTable);
        _group = new Group();
    }

    @Override
    public TableView getTableView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cleanTable() {
        _table.getViewer().getTable().clearAll();

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
