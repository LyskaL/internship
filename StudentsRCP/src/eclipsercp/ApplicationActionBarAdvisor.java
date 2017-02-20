package eclipsercp;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        removeUnWantedActions();
    }

    @Override
    protected void makeActions(IWorkbenchWindow window) {

    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {

    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {

    }

    @SuppressWarnings("restriction")
    private void removeUnWantedActions() {
        ActionSetRegistry asr = WorkbenchPlugin.getDefault().getActionSetRegistry();
        IActionSetDescriptor[] actionSets = asr.getActionSets();

        for (IActionSetDescriptor actionSet : actionSets) {
            IExtension ext = actionSet.getConfigurationElement().getDeclaringExtension();
            asr.removeExtension(ext, new Object[] { actionSet });
        }
    }
}