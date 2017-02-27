package services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Group;
import entities.Student;
import events.table.TableEventListener;

/**
 * The interface allows to control the change data in group and a table.
 *
 * The class implements a interface TableService.
 *
 * This class generates event the change a table.
 *
 *
 * @author Lyska Lyudmila
 */
public class TableServiceImp implements TableService {
    /** Group of student in table **/
    private final Group _group;

    /** A set of listeners that subscribe to events in this class **/
    private final Set<TableEventListener> _listeners;

    /** Selected row in table **/
    private int _selectRows;

    private static final TableServiceImp _tableService;

    static {
        _tableService = new TableServiceImp();
    }

    /**
     * Creates a group of students and a set of listeners.
     */
    private TableServiceImp() {
        _group = new Group();
        _listeners = new HashSet<>();
    }

    /**
     * Gets a link to itself.
     *
     * @return table service
     */
    public static TableServiceImp getInstance() {
        return _tableService;
    }

    @Override
    public void cleanStudents() {
        _group.clean();
        _selectRows = 0;
        notifyListeners();
    }

    @Override
    public void addStudent(Student student) {
        _group.add(student);
        notifyListeners();
    }

    @Override
    public void removeStudent(Student student) {
        _group.remove(student);
        notifyListeners();
    }

    @Override
    public void removeSelectStudent() {
        _group.remove(_selectRows);
        notifyListeners();
    }

    @Override
    public void setStudent(Student student, int index) {
        _group.set(student, index);
        notifyListeners();
    }

    @Override
    public List<Student> getGroup() {
        return Collections.unmodifiableList(_group.getGroup());
    }

    @Override
    public void addTableEventListener(TableEventListener listener) {
        _listeners.add(listener);
    }

    @Override
    public void removeTableEventListener(TableEventListener listener) {
        _listeners.remove(listener);
    }

    @Override
    public Set<TableEventListener> getListeners() {
        return Collections.unmodifiableSet(_listeners);
    }

    /**
     * Sends event for all listeners.
     */
    private void notifyListeners() {
        for (TableEventListener listener : _listeners) {
            listener.tableEvent();
        }
    }

    @Override
    public int getIndex(Student student) {
        return _group.getIndex(student);
    }

    @Override
    public void setIndexSelect(int index) {
        _selectRows = index;
    }

    @Override
    public int getIndexSelect() {
        return _selectRows;
    }
}