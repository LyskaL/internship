package commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import services.TableService;
import services.TableServiceImp;

public class DeleteHandler extends AbstractHandler {

    /** Service for working with data in table **/
    private final TableService _service = TableServiceImp.getInstance();

    /**
     * Processes event pressing the "Delete" button.
     * Gives instructions the table of service to remove a selected row.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        _service.removeSelectStudent();
        return null;
    }

}
