package llyska.module1;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class Draw {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Canvas Example");
		shell.setSize(300, 200);
		shell.setLayout(new RowLayout());
		Text text = new Text(shell, SWT.NONE);
	    shell.open();
	    
	    GC gc = new GC(shell);
	    Color green = display.getSystemColor(SWT.COLOR_GREEN);
	    gc.setForeground(green);
	    gc.drawRectangle(text.getBounds().x-2, text.getBounds().y-2, 
	    				text.getBounds().width+2, text.getBounds().height+2);
	    gc.dispose();
		
	    
		while (!shell.isDisposed()) {
		        if (!display.readAndDispatch()){
		                display.sleep();
		         }
		}
		display.dispose();
	}
}
