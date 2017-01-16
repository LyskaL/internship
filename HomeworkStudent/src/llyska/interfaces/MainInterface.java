package llyska.interfaces;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class MainInterface {
	private Shell _shell;
	
	
	public MainInterface() {
		Display display = new Display();
		_shell = new Shell(display);
		_shell.setText("JFace homework log");
		_shell.setLayout(new GridLayout(1, true));
		
		final int width = 700;
		final int height = 400;
		Monitor monitor = display.getPrimaryMonitor();
		int x = (monitor.getBounds().width / 2) - width / 2;
		int y = (monitor.getBounds().height / 2) - height / 2;
		_shell.setBounds(x, y, width, height);
		setMenuPanel();
		
		_shell.open();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	
	
	
	
	private void setMenuPanel() {
		// TODO Auto-generated method stub
		
	}





	public static void main(String[] args) {
		new MainInterface();
	}
}
