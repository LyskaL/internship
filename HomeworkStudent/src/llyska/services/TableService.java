package llyska.services;

import java.util.List;

import llyska.entities.Student;
import llyska.events.table.TableEventGenerator;

/**
 * The interface allows to control the change data in group and table.
 * The interface implements TableEventGenerator for generating event the change table.
 *
 * @author Lyska Lyudmila
 */
public interface TableService extends TableEventGenerator {
    /** Gets list of student in group **/
    List<Student> getGroup();

    /** Cleans group of student in group **/
    void cleanStudents();

   /**
    * Adds new student in group
    * @param student
    */
    void addStudent(Student student);

    /**
     * Removes student in group
     *
     * @param student for removing
     */
    void removeStudent(Student student);

    /** Removes selected student in table **/
    void removeSelectStudent();

    /**
     * Replaces a old student on the new.
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
