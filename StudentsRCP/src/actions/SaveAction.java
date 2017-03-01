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

public class SaveAction extends Action implements ActionFactory.IWorkbenchAction, ChangeStateEventListener {

    public final static String ID = "StudentsRCP.actions.newAction";

    public SaveAction() {
        setId(ID);
        setToolTipText("Save information to table");
        setEnabled(false);

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/save_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));

        StateService.getInstance().addDataEventListener(this);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void run() {
        CommandUtil.commandRunById("StudentsRCP.commands.Save");
    }

    @Override
    public void handleEvent(ChangeStateEvent event) {
        setEnabled(event.checkState(StateForm.FILLED));
    }
}
