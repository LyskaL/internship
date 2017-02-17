package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import dialog.AddContactDialog;

public class AddContactAction extends Action implements ActionFactory.IWorkbenchAction {
    public final static String ID = "org.eclipsercp.addContact";

    public AddContactAction(IWorkbenchWindow window) {
        setId(ID);
        setText("&Add Contact...");
        setToolTipText("Add a contact to your contacts list.");

        URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/add_contact.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
    }

    @Override
    public void run() {
        System.err.println("AddContactAction.run()");

        AddContactDialog dialog = new AddContactDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        dialog.open();
    }

    @Override
    public void dispose() {
    }

}