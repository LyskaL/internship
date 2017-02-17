package eclipsercp;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import dialog.LoginDialog;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }

    @Override
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(600, 350));
        configurer.setShowCoolBar(true);
        configurer.setShowMenuBar(true);
        configurer.setShowStatusLine(false);
        configurer.setShowPerspectiveBar(false);

        // open login dialog before a main window
        LoginDialog dialog = new LoginDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        if (dialog.open() != Window.OK) {
            System.exit(-1);
        }
    }

    @Override
    public void postWindowCreate() {
        // remove unwanted menu entries
        List<String> unwantedItems = Arrays.asList("org.eclipse.ui.run");
        IMenuManager menuManager = getWindowConfigurer().getActionBarConfigurer().getMenuManager();
        removeUnwantedItems(unwantedItems, menuManager);
    }

    private void removeUnwantedItems(final List<String> unwantedItems, final IMenuManager menuManager) {
        IContributionItem[] items = menuManager.getItems();

        for (IContributionItem item : items) {
            if (item instanceof IMenuManager) {
                removeUnwantedItems(unwantedItems, (IMenuManager) item);
            }

            if (unwantedItems.contains(item.getId())) {
                menuManager.remove(item);
            }
        }
    }
}
