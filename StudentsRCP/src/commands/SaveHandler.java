package commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import events.form.FormEvent;
import services.FormService;

/**
 * Called by on click the "Save" button from menu, toolbar or panel.
 * Sets command to save data from form in a table.
 *
 * @author Lyska Lyudmila
 */
public class SaveHandler extends AbstractHandler {

    /** Service for working with data on form **/
    private final FormService _formService = FormService.getInstance();

    /**
     * Processes event pressing the "Save" button.
     * Gives instructions the form of service to save data from a form to a
     * table.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        _formService.setCommand(FormEvent.FORM_SAVE);
        _formService.runEvent();

        return null;
    }

}
