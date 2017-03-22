package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class stores an information about students.
 *
 * @author Lyska Lyudmila
 */
public class Group {
    private final List<Student> _students;

    /**
     * Fake data
     */
    public Group() {
        _students = new ArrayList<>();

        _students.add(new Student("Lyuda", "1", true));
        _students.add(new Student("Max", "3", true));
        _students.add(new Student("Adam", "1", false));
        _students.add(new Student("Maria", "1", true));
        _students.add(new Student("Denis", "3", true));
        _students.add(new Student("Alex", "1", false));
        _students.add(new Student("Irina", "1", true));
    }

    /**
     * Adds a new student to group.
     *
     * @param student for adding to group
     */
    public void add(Student student) {
        if (student != null) {
            _students.add(student);
        }
    }

    /**
     * Removes a student from group.
     *
     * @param student for removing
     */
    public void remove(Student student) {
        if (student != null) {
            _students.remove(student);
        }
    }

    /**
     * Removes a student at the index from group.
     *
     * @param index of student for removing
     */
    public void remove(int index) {
        if (index < size() && index >= 0) {
            _students.remove(index);
        }
    }

    /**
     * Removes all students from the group.
     */
    public void clean() {
        _students.clear();
    }

    /**
     * Replaces an old student with the new one.
     *
     * @param student for replacing
     * @param index where to write in group
     */
    public void set(Student student, int index) {
        if (index < size() && index >= 0) {
            _students.set(index, student);
        }
    }

    /**
     * Returns a size of group.
     *
     * @return size
     */
    public int size() {
        return _students.size();
    }

    /**
     * Gets a student at the index.
     *
     * @param index for getting
     * @return found student
     */
    public Student getStudent(int index) {
        return (index < size() && index >= 0) ? _students.get(index) : null;
    }

    /**
     * Gets a students list.
     *
     * @return an immutable list of students
     */
    public List<Student> getGroup() {
        return Collections.unmodifiableList(_students);
    }

    /**
     * Gets an index of student.
     *
     * @param student for search in group
     * @return index of student
     */
    public int getIndex(Student student) {
        return _students.indexOf(student);
    }
}
