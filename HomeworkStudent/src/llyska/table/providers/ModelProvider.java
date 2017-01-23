package llyska.table.providers;

import java.util.ArrayList;
import java.util.List;

import llyska.entities.Student;

public enum ModelProvider {
    INSTANCE;

    private List<Student> persons;

    private ModelProvider() {
            persons = new ArrayList<Student>();

            //persons.add(new Student("Rainer", 1, true));
            /*persons.add(new Student("Maris", 3, true));
            persons.add(new Student("Marie", 1, false));
            persons.add(new Student("Holger", 1, true));
            persons.add(new Student("Juliane", 2, false));
            persons.add(new Student("Maris", 3, true));
            persons.add(new Student("Marie", 1, false));
            persons.add(new Student("Holger", 1, true));
            persons.add(new Student("Maris", 3, true));
            persons.add(new Student("Marie", 1, false));
            persons.add(new Student("Holger", 1, true));*/

         /*   _group = new Group();
            _group.add(new Student("Holger", 1, true));
            _group.add(new Student("Juliane", 2, false));
            _group.add(new Student("Maris", 3, true));
            _group.add(new Student("Marie", 1, false));
            _group.add(new Student("Holger", 1, true));
            _group.add(new Student("Maris", 3, true));*/
    }

    public List<Student> getPersons() {
            return persons;
    }

  /*  public Group getPersons() {
        return _group;
    }*/

}