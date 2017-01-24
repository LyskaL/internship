package llyska.table.providers;

import java.util.ArrayList;
import java.util.List;

import llyska.entities.Student;

public enum ModelProvider {
    INSTANCE;

    private List<Student> _persons;

    private ModelProvider() {
            _persons = new ArrayList<Student>();
    }

    public List<Student> getPersons() {
            return _persons;
    }

    public void setPersons(List<Student> persons) {
        if(persons != null) {
            _persons = persons; //copy
        }
    }


}