package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import entities.GroupUsers;

public class AddContactAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

    private final IWorkbenchWindow _window;
    private IStructuredSelection _selection;
    public final static String ID = "org.eclipsercp.addContact";

    public AddContactAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setText("&Add Contact...");
        setToolTipText("Add a contact to your contacts list.");
        setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipsercp", null)); // IImageKeys.ADD_CONTACT
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
            setEnabled(_selection.size() == 1 && _selection.getFirstElement() instanceof GroupUsers);
        } else {
            // other selections (e.g., containing text or of other kinds)
            setEnabled(false);
        }
    }

    @Override
    public void run() {
        AddContactDialog d = new AddContactDialog(_window.getShell());
        int code = d.open();
        if (code == Window.OK) {
            Object item = _selection.getFirstElement();
            GroupUsers group = (GroupUsers) item;
            //User entry = new User(group, d.getNameText(), d.getNickname(), d.getServerText());
            //group.addUser(entry);
        }
        ;
    }

}