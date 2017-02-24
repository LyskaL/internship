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

import commands.CommandUtil;

public class CancelAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

    private final IWorkbenchWindow _window;
    public final static String ID = "StudentsRCP.actions.newAction";
    private IStructuredSelection _selection;

    public CancelAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setText("&Cancel");
        setToolTipText("Clear form panel...");

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/cancel_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
    }

    @Override
    public void dispose() {
        _window.getSelectionService().removeSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        // TODO Auto-generated method stub
        // selection containing elements
        // if (incoming instanceof IStructuredSelection) {
        // selection = (IStructuredSelection) incoming;
        // setEnabled(selection.size() == 1 && selection.getFirstElement() instanceof ContactsGroup);
        // } else {
        // // other selections (e.g., containing text or of other kinds)
        // setEnabled(false);
        // }
    }

    @Override
    public void run() {
        super.run();
        CommandUtil.commandRunById("StudentsRCP.commands.Cancel");
    }
}
