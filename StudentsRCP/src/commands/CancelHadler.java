package commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import events.form.FormEvent;
import services.FormService;

/**
 * Called by on click the "Cancel" button from menu, toolbar or panel.
 * Cleans to filled data from the form.
 *
 * @author Lyska Lyudmila
 */
public class CancelHadler extends AbstractHandler {

    /** Service for working with data on form **/
    private final FormService _formService = FormService.getInstance();

    /**
     * Processes event pressing the "Cancel" button.
     * Gives instructions the form of service
     * to clean data from the panel.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        _formService.setCommand(FormEvent.FORM_CANCEL);
        _formService.runEvent();

        return null;
    }

}
