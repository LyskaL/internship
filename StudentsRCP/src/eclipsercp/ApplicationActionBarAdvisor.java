package eclipsercp;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

import actions.CancelAction;
import actions.DeleteAction;
import actions.NewAction;
import actions.SaveAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction _newAction;
    private IWorkbenchAction _saveAction;
    private IWorkbenchAction _deleteAction;
    private IWorkbenchAction _cancelAction;
    private IWorkbenchAction _helpAction;
    private IWorkbenchAction _aboutAction;
    private IWorkbenchAction _exitAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        removeUnWantedActions();
    }

    @Override
    protected void makeActions(IWorkbenchWindow window) {
        _exitAction = ActionFactory.QUIT.create(window);
        register(_exitAction);
        _helpAction = ActionFactory.HELP_CONTENTS.create(window);
        register(_helpAction);
        _aboutAction = ActionFactory.ABOUT.create(window);
        register(_aboutAction);

        _newAction = new NewAction(window);
        register(_newAction);
        _saveAction = new SaveAction(window);
        register(_saveAction);
        _deleteAction = new DeleteAction(window);
        register(_deleteAction);
        _cancelAction = new CancelAction(window);
        register(_cancelAction);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
//        MenuManager fileMenu = new MenuManager("&File", "file");
//        fileMenu.add(_newAction);
//        fileMenu.add(new Separator());
//        fileMenu.add(_exitAction);
//
//        MenuManager editMenu = new MenuManager("&Edit", "edit");
//        editMenu.add(_saveAction);
//        editMenu.add(_deleteAction);
//        editMenu.add(new Separator());
//        editMenu.add(_cancelAction);
//
//        MenuManager helpMenu = new MenuManager("&Help", "help");
//        helpMenu.add(_helpAction);
//        helpMenu.add(_aboutAction);
//
//        // remove help content
//        IContributionItem[] items = helpMenu.getItems();
//        items[0].setVisible(false);
//
//        menuBar.add(fileMenu);
//        menuBar.add(editMenu);
//        menuBar.add(helpMenu);
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
        // IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
        // coolBar.add(toolbar);
        // toolbar.add(_newAction);
        // toolbar.add(new Separator());
        // toolbar.add(_saveAction);
        // toolbar.add(new Separator());
        // toolbar.add(_deleteAction);
        // toolbar.add(new Separator());
        // toolbar.add(_cancelAction);
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