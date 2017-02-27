package services;

import java.util.List;

import entities.Student;
import events.table.TableEventGenerator;

/**
 * The interface allows to control the change data in group and a table.
 * The interface implements TableEventGenerator for generating event the change a table.
 *
 * @author Lyska Lyudmila
 */
public interface TableService extends TableEventGenerator {
    /** List of student **/
    List<Student> getGroup();

    /** Cleans group **/
    void cleanStudents();

   /**
    * Adds new student to group
    *
    * @param student
    */
    void addStudent(Student student);

    /**
     * Removes student from group
     *
     * @param student for removing
     */
    void removeStudent(Student student);

    /** Removes selected student from table **/
    void removeSelectStudent();

    /**
     * Replaces an old student with the new one.
     *
     * @param student for replacing
     * @param index where to write in group
     */
    void setStudent(Student student, int index);

    /**
     * Gets a index of student.
     *
     * @param student for search in group
     * @return index of student
     */
    int getIndex(Student student);

    /**
     * Saves a index selected student.
     *
     * @param index for saving
     */
    void setIndexSelect(int index);

    /**
     * Gets a index selected student.
     *
     * @return index selected student
     */
    int getIndexSelect();
}