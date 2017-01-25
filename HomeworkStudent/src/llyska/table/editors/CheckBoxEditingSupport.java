package llyska.table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;

import llyska.entities.Student;

public class CheckBoxEditingSupport extends CellEditingSupport {

    private final TableViewer viewer;

    public CheckBoxEditingSupport(TableViewer viewer) {
        super(viewer);
        this.viewer = viewer;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);

    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        Student person = (Student) element;
        return person.isSwtDone();

    }

    @Override
    protected void setValue(Object element, Object value) {
        super.setValue(element, value);
        Student student = (Student) element;
        student.setSwtDone((Boolean) value);
        viewer.update(element, null);
    }
}