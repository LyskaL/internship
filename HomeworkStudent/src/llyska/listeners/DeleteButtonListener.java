package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.state.ChangeStateEvent;
import llyska.services.StateService;
import llyska.services.TableService;
import llyska.util.Constants;

/**
 * The class implements Listener.
 * The class is event.
 * Called by on click the "Delete" button.
 * Removes a selected row from table.
 *
 * @author Lyska Lyudmila
 */
public class DeleteButtonListener implements Listener {
    /** Service for working with data on table **/
    private final TableService _service = Constants.TABLE_SERVICE;
    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService;

    public DeleteButtonListener() {
        _stateService = StateService.getInstance();
    }

    /**
     * Processes event pressing the "Delete" button.
     * Gives instructions the table of service to remove a selected row.
     * Gives instructions the state service to disable "Delete" button.
     */
    @Override
    public void handleEvent(Event event) {
        _service.removeSelectStudent();
        _stateService.disableState(ChangeStateEvent.TABLE_SELECTED);
        _stateService.runEvent();
    }

}
