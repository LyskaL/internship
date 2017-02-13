package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import editors.ChatEditor;
import editors.ChatEditorInput;
import entities.User;

public class ChatAction extends Action implements ISelectionListener, IWorkbenchAction {
    private final IWorkbenchWindow _window;
    public final static String ID = "actions.chat";
    private IStructuredSelection _selection;

    public ChatAction(IWorkbenchWindow window) {
        this._window = window;
        setId(ID);
        setText("&Chat");
        setToolTipText("Chat with the selected contact.");

        URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/chat_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));

        window.getSelectionService().addSelectionListener(this);
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
        if (incoming instanceof IStructuredSelection) {
            _selection = (IStructuredSelection) incoming;
            setEnabled(_selection.size() == 1 && _selection.getFirstElement() instanceof User);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void run() {
        Object item = _selection.getFirstElement();
        User entry = (User) item;
        IWorkbenchPage page = _window.getActivePage();
        ChatEditorInput input = new ChatEditorInput(entry.getName());
        try {
            page.openEditor(input, ChatEditor.ID);
        } catch (PartInitException e) {
            // Handle error.
        }
    }

    @Override
    public void dispose() {
        _window.getSelectionService().removeSelectionListener(this);
    }
}
