package llyska.table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

import llyska.events.ChangeStateButtonEvent;
import llyska.services.StateButtonService;

public class CellEditingSupport extends EditingSupport {
    private final StateButtonService _stateButtonService;


    public CellEditingSupport(ColumnViewer viewer) {
        super(viewer);
        _stateButtonService = StateButtonService.getInstance();
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        return null;
    }

    @Override
    protected boolean canEdit(Object element) {
        return false;
    }

    @Override
    protected Object getValue(Object element) {
        return null;
    }

    @Override
    protected void setValue(Object element, Object value) {
        ChangeStateButtonEvent.getInstance().setStateButton(ChangeStateButtonEvent.TABLE_EDITED|ChangeStateButtonEvent.TABLE_SELECTED);
        _stateButtonService.runEvent();
    }

}
