package llyska.interfaces;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import llyska.services.*;
import llyska.util.Constants;

/**
 * Class MainInterface starts our program.
 * Calculator has two tabs such as "Calculator" and "History".
 * It performs mathematical operations such as addition, subtraction,
 * multiplication and division.
 * The result of calculations are saved in history tab.
 * 
 * @author Lyska Lyudmila
 */
public class MainInterface {
	private Shell _shell;
	private CTabFolder _folder;
	
	public MainInterface() {
		Display display = new Display();
		_shell = new Shell(display);
		Constants.setDisplay(display);
		_shell.setText("Calculator");
		_shell.setLayout(new GridLayout(1, true));

		final int width = 300;
		final int height = 400;
		Monitor monitor = display.getPrimaryMonitor();
		int x = (monitor.getBounds().width / 2) - width / 2;
		int y = (monitor.getBounds().height / 2) - height / 2;
		_shell.setBounds(x, y, width, height);
		setTabsPanel();
		
		_shell.open();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}


	private void setTabsPanel() {
		_folder = new CTabFolder(_shell, SWT.NONE);
		_folder.setBackground(_shell.getBackground());
		_folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		CTabItem calculator = new CTabItem(_folder, SWT.NONE);
		calculator.setText("Calculator");
		calculator.setControl(new CalculatorView(_folder, SWT.NONE));
		
		CTabItem history = new CTabItem(_folder, SWT.NONE);
		history.setText("History");
		HistoryManager historyTab = ServiceProvider.getService(HistoryManager.class);
		historyTab.setParentToHistoryView(_folder);
		history.setControl(historyTab.getCompositeToHistoryView());
		ToolBar bar = new ToolBar(_shell, SWT.BORDER);
		bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}

	public static void main(String[] args) {
		new MainInterface();
	}

}
