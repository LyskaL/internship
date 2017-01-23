package llyska.jface.tableexample;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class MainInterface {
    private final Shell _shell;

    public MainInterface() {
        Display display = new Display();
        Constance.setDisplay(display);

        _shell = new Shell(display);
        _shell.setText("JFace homework log");
        _shell.setLayout(new GridLayout(1, true));

        final int width = 700;
        final int height = 300;
        Monitor monitor = display.getPrimaryMonitor();
        int x = (monitor.getBounds().width / 2) - width / 2;
        int y = (monitor.getBounds().height / 2) - height / 2;
        _shell.setBounds(x, y, width, height);

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
        View table =  new View();
        table.createPartControl(tablePanel);
    }

    public static void main(String[] args) {
        new MainInterface();
    }
}