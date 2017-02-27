package table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;

import entities.Student;

/**
 * The class is designed for editing cell with a check of button.
 * By clicking on cell with button to change the value on the opposite.
 *
 * @author Lyska Lyudmila
 */
public class CheckBoxEditingSupport extends EditingSupport {

    /** Table with values **/
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
        Student student = (Student) element;
        student.setSwtDone((Boolean) value);
        viewer.update(element, null);
    }
}