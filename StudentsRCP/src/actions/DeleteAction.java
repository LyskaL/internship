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
import entities.Student;
import services.TableService;
import services.TableServiceImp;

public class DeleteAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

    public final static String ID = "StudentsRCP.actions.newAction";

    private final IWorkbenchWindow _window;
    private IStructuredSelection _selection;

    /** Service for working with data in table **/
    private final TableService _service = TableServiceImp.getInstance();

    public DeleteAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setToolTipText("Delete information from table");

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/delete_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));

        _window.getSelectionService().addSelectionListener(this);
    }

    @Override
    public void dispose() {
        _window.getSelectionService().removeSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        // selection containing elements
        if (selection instanceof IStructuredSelection) {
            _selection = (IStructuredSelection) selection;
            if (_selection.size() == 1 && _selection.getFirstElement() instanceof Student) {
                _service.setIndexSelect(_service.getIndex((Student) _selection.getFirstElement()));
                setEnabled(true);
                return;
            }
        }
        setEnabled(false);
    }

    @Override
    public void run() {
        CommandUtil.commandRunById("StudentsRCP.commands.Delete");
    }

}
