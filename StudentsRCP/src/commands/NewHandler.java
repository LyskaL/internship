package commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import services.TableService;
import services.TableServiceImp;

/**
 * Called by on click the "New" button from menu, toolbar or panel.
 * Cleans all data from table.
 *
 * @author Lyska Lyudmila
 */
public class NewHandler extends AbstractHandler {

    /** Service for working with data on table **/
    private final TableService _service = TableServiceImp.getInstance();

    /**
     * Processes event pressing the "New" button.
     * Gives instructions the table of service to clean a table.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        Display display = PlatformUI.getWorkbench().getDisplay();
        boolean answer = MessageDialog.openQuestion(display.getActiveShell(),
                         "Create new table",
                         "Are you sure you want to create a new table?");
        if(answer) {
            _service.cleanStudents();
        }

        return null;
    }

}