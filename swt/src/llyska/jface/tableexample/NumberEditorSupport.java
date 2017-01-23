package llyska.jface.tableexample;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;


public class NumberEditingSupport  extends EditingSupport {
    private final TableViewer _viewer;
    private final CellEditor _editor;

    public NumberEditingSupport(TableViewer viewer) {
        super(viewer);
        _viewer = viewer;
        _editor = new NumberEditing(viewer.getTable());
        /*_editor.setValidator(new ICellEditorValidator() {
            @Override
            public String isValid(Object value) {
                try {
                  Long.parseLong((String) value);
                } catch (NumberFormatException e) {
                  return "No valid integer value!";
                }
                return null;
              }
            });*/
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
        return ((Person) element).getLastName();
    }

    @Override
    protected void setValue(Object element, Object userInputValue) {
        //заходит после изменени€ €чейки
        ((Person) element).setLastName(String.valueOf(userInputValue));
        _viewer.update(element, null);
    }

}