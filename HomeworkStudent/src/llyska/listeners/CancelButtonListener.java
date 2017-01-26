package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.form.FormEvent;
import llyska.services.FormEventService;

/**
 * The class implements Listener.
 * The class is event.
 * Called by on click the "Cancel" button.
 * Cleans to filled data from the form.
 *
 * @author Lyska Lyudmila
 */
public class CancelButtonListener implements Listener {
    /** Service for working with data on form **/
    private final FormEventService _formService = FormEventService.getInstance();;

    /**
     * Processes event pressing the "Cancel" button.
     * Gives instructions the form of service to clean data from the panel.
     */
    @Override
    public void handleEvent(Event event) {
        _formService.setCommand(FormEvent.FORM_CANCEL);
        _formService.runEvent();
    }

}
