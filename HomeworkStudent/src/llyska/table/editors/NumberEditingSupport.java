package llyska.table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import llyska.entities.Student;

/**
 * The class is designed for editing cell with
 * a group number where student studies.
 *
 * Allows to enter only numbers.
 *
 * The new value is immediately saved to the table.
 *
 * @author Lyska Lyudmila
 *
 */
public class NumberEditingSupport extends EditingSupport {
    private final TableViewer _viewer;
    private final CellEditor _editor;

    public NumberEditingSupport(TableViewer viewer) {
        super(viewer);
        _viewer = viewer;
        _editor = new NumbersCellEditor(viewer.getTable());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return _editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        Student student = (Student)element;
        return String.valueOf(student.getNumberGroup());
    }

    @Override
    protected void setValue(Object element, Object value) {
        Student student = (Student) element;
        student.setNumberGroup((String)value);
        _viewer.update(element, null);
    }

}