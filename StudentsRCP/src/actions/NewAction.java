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

import entities.Student;

public class NewAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

    private final IWorkbenchWindow _window;
    public final static String ID = "StudentsRCP.actions.newAction";
    private IStructuredSelection _selection;

    public NewAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setText("&New");
        setToolTipText("New table...");

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/new_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
        _window.getSelectionService().addSelectionListener(this);
    }

    @Override
    public void dispose() {
        _window.getSelectionService().removeSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        System.out.println("selectionChanged from NewAction");
        // selection containing elements
        if (selection instanceof IStructuredSelection) {
            _selection = (IStructuredSelection) selection;
            setEnabled(_selection.size() == 1 && _selection.getFirstElement() instanceof Student);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void run() {
        super.run();

    }
}
