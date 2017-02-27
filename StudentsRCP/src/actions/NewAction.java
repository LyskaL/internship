package actions;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

import commands.CommandUtil;

public class NewAction extends Action implements ActionFactory.IWorkbenchAction {

    public final static String ID = "StudentsRCP.actions.newAction";

    public NewAction(IWorkbenchWindow window) {
        setId(ID);
        setText("&New");
        setToolTipText("New table...");

        URL url = Platform.getBundle("StudentsRCP").getEntry("icons/new_icon.png");
        setImageDescriptor(ImageDescriptor.createFromURL(url));
    }

    @Override
    public void dispose() {
    }

    @Override
    public void run() {
        super.run();

        //to call tester
        //IEvaluationService evaluationService = PlatformUI.getWorkbench().getService(IEvaluationService.class);
        //evaluationService.requestEvaluation("studentsrcp.tester.isEnabledState");

        CommandUtil.commandRunById("StudentsRCP.commands.New");
    }
}
