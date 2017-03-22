package eclipsercp;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    @SuppressWarnings("restriction")
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);

        ActionSetRegistry asr = WorkbenchPlugin.getDefault().getActionSetRegistry();
        IActionSetDescriptor[] actionSets = asr.getActionSets();

        for (IActionSetDescriptor actionSet : actionSets) {
            IExtension ext = actionSet.getConfigurationElement().getDeclaringExtension();
            asr.removeExtension(ext, new Object[] { actionSet });
        }
    }
}