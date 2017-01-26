package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.state.ChangeStateEvent;
import llyska.services.StateService;
import llyska.services.TableService;
import llyska.util.Constants;

public class NewButtonListener implements Listener {
    private final StateService _stateService;
    private final TableService _service = Constants.TABLE_SERVICE;

    public NewButtonListener() {
        _stateService = StateService.getInstance();
    }

    @Override
    public void handleEvent(Event event) {
        _service.cleanStudents();
        _stateService.disableState(ChangeStateEvent.TABLE_SELECTED);
        _stateService.runEvent();
    }
}
