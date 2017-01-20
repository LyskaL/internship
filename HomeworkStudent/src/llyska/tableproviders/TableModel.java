package llyska.tableproviders;

import llyska.entities.Student;

public class TableModel {
    private final String _name;
    private final int _number;
    private final boolean _isCheck;

    public TableModel(Student student, int numberGroup) {
        _name = student.getName();
        _number = numberGroup;
        _isCheck = student.isSwtDone();
    }

    @Override
    public String toString() {
        return getName()+" "+String.valueOf(isCheck())+" "+String.valueOf(getNumber());
    }

    public String getName() {
        return _name;
    }

    public boolean isCheck() {
        return _isCheck;
    }

    public int getNumber() {
        return _number;
    }
}
