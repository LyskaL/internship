package llyska.table.providers;

import llyska.entities.Student;

/**
 * Model is responsible for how will look one row of table.
 *
 * @author Lyska Lyudmila
 *
 */
public class TableModel {
    /** Name of student **/
    private final String _name;
    /** Number of group where studying a student **/
    private final int _number;
    /** Student done task or not **/
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

    /**
     * Gets a name of a student
     *
     * @return name of student
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets information done task or not.
     * @return student to task is done or not
     */
    public boolean isCheck() {
        return _isCheck;
    }

    /**
     * Gets a number of group where studying student.
     *
     * @return number of group
     */
    public int getNumber() {
        return _number;
    }
}
