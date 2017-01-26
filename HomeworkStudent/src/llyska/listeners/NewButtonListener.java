package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.state.ChangeStateEvent;
import llyska.services.StateService;
import llyska.services.TableService;
import llyska.util.Constants;

/**
 * The class implements Listener. The class is event.
 * Called by on click the "New" button.
 * Cleans all data from table.
 *
 * @author Lyska Lyudmila
 */
public class NewButtonListener implements Listener {
    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService;
    /** Service for working with data on table **/
    private final TableService _service = Constants.TABLE_SERVICE;

    public NewButtonListener() {
        _stateService = StateService.getInstance();
    }

    /**
     * Processes event pressing the "New" button.
     * Gives instructions the table of service to clean a table.
     * Gives instructions the state service to disable TABLE_SELECTED.
     */
    @Override
    public void handleEvent(Event event) {
        _service.cleanStudents();
        _stateService.disableState(ChangeStateEvent.TABLE_SELECTED);
        _stateService.runEvent();
    }
}
