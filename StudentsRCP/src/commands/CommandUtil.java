package commands;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * Set static methods for working with commands
 *
 * @author Lyska Lyudmila
 */
public class CommandUtil {

    /**
     * Launches handler by id commands
     *
     * @param commandID string with id command
     */
    public static void commandRunById(String commandID) {
        try {
            ICommandService commandService = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getService(ICommandService.class);

            Command command = commandService.getCommand(commandID);
            command.getHandler().execute(new ExecutionEvent());

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
