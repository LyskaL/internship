package entities;

/**
 * The student class stores a data about a student:
 * its name, group number and if the task is done or not.
 *
 * @author Lyska Lyudmila
 */
public class Student {

    /** Name of student **/
    private String _name;

    /** Number of group where a student studies **/
    private String _numberGroup;

    /** Task done status **/
    private boolean _swtDone;

    /**
     * Constructor to create a student.
     *
     * @param name of a student
     * @param numberGroup student's group name
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
     * Gets information about the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isSwtDone() {
        return _swtDone;
    }

    /**
     * Sets task status.
     *
     * @param swtDone true if the task is done, false otherwise
     */
    public void setSwtDone(boolean swtDone) {
        _swtDone = swtDone;
    }

    /**
     * Gets a group name where student studies.
     *
     * @return group name
     */
    public String getNumberGroup() {
        return _numberGroup;
    }

    /**
     * Sets a group name where student studies.
     *
     * @param numberGroup group name
     */
    public void setNumberGroup(String numberGroup) {
        _numberGroup = numberGroup;
    }

    /**
     * Set a name of student
     *
     * @param name name of student
     */
    public void setName(String name) {
        _name = name;
    }

    @Override
    public String toString() {
        return "Student [_name=" + _name + ", _numberGroup=" + _numberGroup + ", _swtDone=" + _swtDone + "]";
    }
}
