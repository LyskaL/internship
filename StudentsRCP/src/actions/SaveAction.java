package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.actions.ActionFactory;

import commands.CommandUtil;

public class SaveAction extends Action implements ActionFactory.IWorkbenchAction {

    public final static String ID = "StudentsRCP.actions.newAction";

    public SaveAction() {
        setId(ID);
        setText("&Save");
        setToolTipText("Save information to table");
        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/save_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
    }

    @Override
    public void dispose() {}

    @Override
    public void run() {
        CommandUtil.commandRunById("StudentsRCP.commands.Save");
    }
}
