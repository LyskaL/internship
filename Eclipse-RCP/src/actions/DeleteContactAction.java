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
import services.GroupService;
import services.GroupServiceImpl;

public class DeleteContactAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

    private final IWorkbenchWindow _window;
    private IStructuredSelection _selection;
    private final GroupService _service;
    public final static String ID = "org.eclipsercp.deleteContact";

    public DeleteContactAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setText("&Delete Contact...");
        setToolTipText("Delete a contact from your contacts list.");

        URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/delete_contact.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
        _service = GroupServiceImpl.getInstance();
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
        _service.removeUser(((User)_selection.getFirstElement()));
    }

}