package llyska.table.providers;

import java.util.ArrayList;
import java.util.List;

import llyska.entities.Student;


/**
 * Model is responsible for how will display information in table.
 * Stores list of student that save in table.
 *
 * @author Lyska Lyudmila
 */
public enum ModelProvider {
    INSTANCE;

    private List<Student> _persons;

    private ModelProvider() {
            _persons = new ArrayList<Student>();
    }

    /**
     * Gets a list of student.
     *
     * @return list of student
     */
    public List<Student> getPersons() {
            return _persons;
    }

    /**
     * Sets a list of student for a model of table.
     *
     * @param persons for model of table
     */
    public void setPersons(List<Student> persons) {
        if(persons != null) {
            _persons = persons;
        }
    }


}