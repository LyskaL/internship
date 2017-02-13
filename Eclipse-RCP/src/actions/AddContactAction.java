package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

import entities.User;

public class AddContactAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

    private final IWorkbenchWindow _window;
    private IStructuredSelection _selection;
    public final static String ID = "org.eclipsercp.addContact";

    public AddContactAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setText("&Add Contact...");
        setToolTipText("Add a contact to your contacts list.");

        URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/add_contact.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));

        _window.getSelectionService().addSelectionListener(this);
    }

    @Override
    public void dispose() {
        _window.getSelectionService().removeSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
        // selection containing elements
        if (incoming instanceof IStructuredSelection) {
            _selection = (IStructuredSelection) incoming;
            setEnabled(_selection.size() == 1 && _selection.getFirstElement() instanceof User);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void run() {
       //TODO
    }

}