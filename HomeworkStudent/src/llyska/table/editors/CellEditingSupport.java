package llyska.table.editors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

import llyska.events.state.ChangeStateEvent;
import llyska.services.StateService;

public class CellEditingSupport extends EditingSupport {
    private final StateService _stateService;


    public CellEditingSupport(ColumnViewer viewer) {
        super(viewer);
        _stateService = StateService.getInstance();
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
        _stateService.enableState(ChangeStateEvent.TABLE_EDITED);
        _stateService.runEvent();
    }

}
