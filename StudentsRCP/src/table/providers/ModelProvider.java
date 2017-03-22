package table.providers;

import java.util.ArrayList;
import java.util.List;

import entities.Student;

/**
 * Model is responsible for how will display information in table.
 * Stores list of student that save in table.
 *
 * @author Lyska Lyudmila
 */
public enum ModelProvider {
    INSTANCE;

    private List<Student> _students;

    private ModelProvider() {
        _students = new ArrayList<Student>();
    }

    /**
     * Gets a list of student.
     *
     * @return list of student
     */
    public List<Student> getStudents() {
        return _students;
    }

    /**
     * Sets a list of student for a model of table.
     *
     * @param students for model of table
     */
    public void setStudents(List<Student> students) {
        if (students != null) {
            _students = students;
        }
    }

}