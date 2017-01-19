package llyska.module1;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class TestSashForm {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell(display);
		shell.setText("SashForm Example");
		shell.setLayout(new FillLayout());
		
		final int width = 600;
		final int height = 400;
		Monitor monitor = display.getPrimaryMonitor();
		int x = (monitor.getBounds().width / 2) - width / 2;
		int y = (monitor.getBounds().height / 2) - height / 2;
		shell.setBounds(x, y, width, height);

	    // Create the SashForm with HORIZONTAL
	    SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL);
	    new Button(sashForm, SWT.NONE).setText("Left");
	    new Button(sashForm, SWT.NONE).setText("Right");
		
	    // Create the SashForm with VERTICAL
	    SashForm sashForm2 = new SashForm(shell, SWT.VERTICAL);
	    new Button(sashForm2, SWT.PUSH).setText("Up");
	    new Button(sashForm2, SWT.PUSH).setText("Down");
		
		shell.open();
	 
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

}
