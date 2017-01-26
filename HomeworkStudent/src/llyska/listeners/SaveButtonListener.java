package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.form.FormEvent;
import llyska.services.FormEventService;

/**
 * The class implements Listener. The class is event.
 * Called by on click the "Save" button.
 * Saves data from form in a
 * table.
 *
 * @author Lyska Lyudmila
 */
public class SaveButtonListener implements Listener {
    /** Service for working with data on form **/
    private final FormEventService _formService = FormEventService.getInstance();;

    /**
     * Processes event pressing the "Save" button.
     * Gives instructions the form of service to save data from a form to a table.
     */
    @Override
    public void handleEvent(Event event) {
        _formService.setCommand(FormEvent.FORM_SAVE);
        _formService.runEvent();
    }
}
