package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import llyska.util.Constants;

/**
 * The class is main interface our program.
 * It consists of some components such as: MenuView, TableView and FormView.
 *
 * @author Lyska Lyudmila
 */
public class MainInterface {
    private final Shell _shell;
    private MenuView _menu;

    /**
     * Constructor this class.
     * Creates shell, sets size window,
     * adds menu and main panel, starts processing events.
     */
    public MainInterface() {
        Display display = new Display();
        Constants.setDisplay(display);

        _shell = new Shell(display);
        _shell.setText("Task Students");
        _shell.setLayout(new GridLayout(1, true));

        final int width = 700;
        final int height = 300;
        Monitor monitor = display.getPrimaryMonitor();
        int x = (monitor.getBounds().width / 2) - width / 2;
        int y = (monitor.getBounds().height / 2) - height / 2;
        _shell.setBounds(x, y, width, height);

        setMenuPanel();
        setMainPanel();

        _shell.open();
        while (!_shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    /**
     * Split main panel on two part by using SashForm.
     *
     * On first part adds TableView (table with data).
     * On second part adds FormView (form with text fields for filling data about a student
     * and a panel with buttons for management other components.)
     */
    private void setMainPanel() {
        SashForm sashForm = new SashForm(_shell, SWT.HORIZONTAL | SWT.NONE);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite tablePanel = new Composite(sashForm, SWT.BORDER);
        tablePanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tablePanel.setLayout(new GridLayout(1, true));

        new TableView(tablePanel);

        Composite formPanel = new Composite(sashForm, SWT.BORDER);
        formPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        formPanel.setLayout(new GridLayout(1, true));
        new FormView(formPanel, SWT.NONE);
    }

    /**
     * Adds a menu on shell.
     */
    private void setMenuPanel() {
        _menu = new MenuView(_shell, SWT.BAR);
        _shell.setMenuBar(_menu.getMenu());
    }

    /**
     * Starts our application
     */
    public static void main(String[] args) {
        new MainInterface();
    }
}
