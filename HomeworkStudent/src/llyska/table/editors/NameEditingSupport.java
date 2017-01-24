package llyska.table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import llyska.entities.Student;

public class NameEditingSupport extends EditingSupport {
    private final TableViewer viewer;
    private final CellEditor editor;



    public NameEditingSupport(TableViewer viewer) {
        super(viewer);
        this.viewer = viewer;
        this.editor = new TextCellEditor(viewer.getTable());
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return editor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        return ((Student) element).getName();
    }

    @Override
    protected void setValue(Object element, Object userInputValue) {
        //������� ����� ��������� ������
        //((Student) element).setName(String.valueOf(userInputValue));
        viewer.update(element, null);
    }

}
