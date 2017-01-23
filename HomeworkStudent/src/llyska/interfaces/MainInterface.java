package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import llyska.services.TableServiceImp;
import llyska.util.Constants;

public class MainInterface {
    private final Shell _shell;
    private MenuView _menu;

    public MainInterface() {
        Display display = new Display();
        Constants.setDisplay(display);

        _shell = new Shell(display);
        _shell.setText("JFace homework log");
        _shell.setLayout(new GridLayout(1, true));

        final int width = 700;
        final int height = 250;
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

    private void setMainPanel() {
        SashForm sashForm = new SashForm(_shell, SWT.HORIZONTAL | SWT.NONE);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite tablePanel = new Composite(sashForm, SWT.BORDER);
        tablePanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tablePanel.setLayout(new GridLayout(1, true));

        new TableServiceImp(tablePanel);

        Composite formPanel = new Composite(sashForm, SWT.BORDER);
        formPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        formPanel.setLayout(new GridLayout(1, true));
        new FormView(formPanel, SWT.NONE);
    }

    private void setMenuPanel() {
        _menu = new MenuView(_shell, SWT.BAR);
        _shell.setMenuBar(_menu.getMenu());
    }

    public static void main(String[] args) {
        new MainInterface();
    }
}
