package eclipsercp;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

import actions.AddContactAction;
import actions.DeleteContactAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction _exitAction;
    private IWorkbenchAction _aboutAction;
    private IWorkbenchAction _addContactAction;
    private IWorkbenchAction _deleteContactAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        removeUnWantedActions();
    }

    @Override
    protected void makeActions(IWorkbenchWindow window) {
        _exitAction = ActionFactory.QUIT.create(window);
        register(_exitAction);
        _aboutAction = ActionFactory.ABOUT.create(window);
        register(_aboutAction);
        _addContactAction = new AddContactAction(window);
        register(_addContactAction);
        _deleteContactAction = new DeleteContactAction(window);
        register(_deleteContactAction);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager appMenu = new MenuManager("&App", "_app");
        appMenu.add(_addContactAction);
        appMenu.add(_deleteContactAction);
        appMenu.add(new Separator());

        MenuManager helpMenu = new MenuManager("&Help", "_help");
        helpMenu.add(_aboutAction);

        appMenu.add(helpMenu);
        appMenu.add(new Separator());
        appMenu.add(_exitAction);

        menuBar.add(appMenu);
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
        IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
        coolBar.add(toolbar);
        toolbar.add(_addContactAction);
        toolbar.add(new Separator());
        toolbar.add(_deleteContactAction);
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