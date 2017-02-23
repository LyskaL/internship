package commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class CancelHadler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Display display = PlatformUI.getWorkbench().getDisplay();
        MessageDialog.openInformation(display.getActiveShell(), "Cancel Command", "Cancel executed.");
        return null;
    }

}
