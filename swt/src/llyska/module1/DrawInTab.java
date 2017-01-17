package llyska.module1;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class DrawInTab {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Canvas Example");
		shell.setSize(300, 200);
		shell.setLayout(new GridLayout(1, true));
		
		Composite panel = new Composite(shell, SWT.BORDER);
		panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		panel.setLayout(new GridLayout(1, true));
		
		CTabFolder _folder = new CTabFolder(panel, SWT.NONE);
		_folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		CTabItem tab = new CTabItem(_folder, SWT.NONE);
		tab.setText("TAB");
		
		Composite panelTab = new Composite(_folder, SWT.NONE);
		panelTab.setLayout(new GridLayout(1, true));
		panelTab.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tab.setControl(panelTab);
		
		Canvas canvas = new Canvas(panelTab, SWT.NONE);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Text text = new Text(canvas, SWT.NONE);
		int x = 0;
		int y = 0;
		int w = 100;
		int h = 20;
		text.setBounds(x+1, y+1, w, h);
	    canvas.addPaintListener(new PaintListener() {
	      public void paintControl(PaintEvent e) {
	    	  Color red = display.getSystemColor(SWT.COLOR_RED);
	    	  e.gc.setForeground(red);
	    	  e.gc.drawRectangle(x, y, w+1, h+1);
	      }
	    });
		
		shell.open();
		while (!shell.isDisposed()) {
		        if (!display.readAndDispatch()){
		                display.sleep();
		         }
		}
		display.dispose();
	}
}
