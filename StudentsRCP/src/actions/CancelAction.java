package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.actions.ActionFactory;

import commands.CommandUtil;

public class CancelAction extends Action implements ActionFactory.IWorkbenchAction {

    public final static String ID = "StudentsRCP.actions.newAction";

    public CancelAction() {
        setId(ID);
        setText("&Cancel");
        setToolTipText("Clear form panel...");

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/cancel_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
    }

    @Override
    public void dispose() {
    }

    @Override
    public void run() {
        CommandUtil.commandRunById("StudentsRCP.commands.Cancel");
    }
}
