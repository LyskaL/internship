package llyska.module1;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class FirstWindow {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.open();
		shell.setText("My First SWT Window!");
		shell.setBounds(500, 200, 300, 300);
		
		Text helloWorldTest = new Text(shell, SWT.NONE);
		helloWorldTest.setBounds(70, 120, 100, 20);
		helloWorldTest.setBackground(shell.getBackground());
		helloWorldTest.setText("My FIRST SWT WINDOW");
		helloWorldTest.pack();
		Color red = new Color(display, 255, 0, 0);
		helloWorldTest.setForeground(red);
		helloWorldTest.setToolTipText("ToolTip :)");
		// run the event loop as long as the window is open
		while (!shell.isDisposed()) {
		    // read the next OS event queue and transfer it to a SWT event
		        if (!display.readAndDispatch())
		         {
		        // if there are currently no other OS event to process
		        // sleep until the next OS event is available
		                display.sleep();
		         }
		}
		// disposes all associated windows and their components
		display.dispose();
	}

}
