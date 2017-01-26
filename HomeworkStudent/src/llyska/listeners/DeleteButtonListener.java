package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.state.ChangeStateEvent;
import llyska.services.StateService;
import llyska.services.TableService;
import llyska.util.Constants;

public class DeleteButtonListener implements Listener {
    private final TableService _service = Constants.TABLE_SERVICE;
    private final StateService _stateService;

    public DeleteButtonListener() {
        _stateService = StateService.getInstance();
    }
    @Override
    public void handleEvent(Event event) {
        _service.removeSelectStudent();
        _stateService.disableState(ChangeStateEvent.TABLE_SELECTED);
        _stateService.runEvent();
    }

}
