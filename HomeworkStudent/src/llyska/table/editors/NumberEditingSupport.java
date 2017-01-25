package llyska.table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;

import llyska.entities.Student;

public class NumberEditingSupport extends CellEditingSupport {
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
    protected void setValue(Object element, Object userInputValue) {
        super.setValue(element, userInputValue);
        _viewer.update(element, null);
    }

}