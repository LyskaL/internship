package llyska.entities;

/**
 * The student class stores a data about a student.
 * Its name, group number and if the task is done or not.
 *
 * @author Lyska Lyudmila
 */
public class Student {

    /** Name of student **/
    private String _name;

    /** Number of group where studying a student **/
    private String _numberGroup;

    /** Student done task or not **/
    private boolean _swtDone;

    /**
     * Constructor to create a student.
     *
     * @param name of student
     * @param numberGroup where studying student
     * @param swtDone is done the task or not
     */
    public Student(String name, String numberGroup, boolean swtDone) {
        _name = name;
        _swtDone = swtDone;
        _numberGroup = numberGroup;
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
    public boolean isSwtDone() {
        return _swtDone;
    }

    /**
     * Sets a information to student done task or not.
     *
     * @param swtDone
     */
    public void setSwtDone(boolean swtDone) {
        _swtDone = swtDone;
    }

    /**
     * Gets a number of group where studying student.
     *
     * @return number of group
     */
    public String getNumberGroup() {
        return _numberGroup;
    }

    /**
     * Sets a number of group where studying student.
     *
     * @param numberGroup number of group
     */
    public void setNumberGroup(String numberGroup) {
        _numberGroup = numberGroup;
    }

    /**
     * Set a name of student
     *
     * @param name student
     */
    public void setName(String name) {
        _name = name;
    }

    @Override
    public String toString() {
        return "Student [_name=" + _name + ", _numberGroup=" + _numberGroup + ", _swtDone=" + _swtDone + "]";
    }
}
