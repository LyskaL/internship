package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.actions.ActionFactory;

import commands.CommandUtil;
import events.state.ChangeStateEvent;
import events.state.ChangeStateEventListener;
import services.StateForm;
import services.StateService;

public class CancelAction extends Action implements ActionFactory.IWorkbenchAction, ChangeStateEventListener {

    public final static String ID = "StudentsRCP.actions.newAction";

    public CancelAction() {
        setId(ID);
        setToolTipText("Clear form panel...");
        setEnabled(false);

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/cancel_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));

        StateService.getInstance().addDataEventListener(this);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void run() {
        CommandUtil.commandRunById("StudentsRCP.commands.Cancel");
    }

    @Override
    public void handleEvent(ChangeStateEvent event) {
        setEnabled(event.checkState(StateForm.FILLED));
    }
}
