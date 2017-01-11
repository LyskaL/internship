package llyska.module1;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class TestLayout {
	
	public TestLayout() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Test layout");
		shell.setLayout(new FillLayout());
		shell.setBounds(800, 300, 500, 500);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);
		
		Button b1 = new Button(shell, SWT.PUSH);
		b1.setText("B1");
		b1.setLayoutData(new GridData());
		GridData grid = new GridData();
		grid.horizontalAlignment = GridData.FILL;
		grid.grabExcessHorizontalSpace = true;
		b1.setLayoutData(grid);
		
		new Button(shell, SWT.PUSH).setText("Wide Button 2");
		new Button(shell, SWT.PUSH).setText("Button 3");
		
		Button b4 = new Button(shell, SWT.PUSH);
		b4.setText("B4");
        b4.setLayoutData(new GridData (SWT.FILL, SWT.FILL, true, true, 2, 1));
        b4.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			display.close();
		}});
		
		new Button(shell, SWT.PUSH).setText("Button 5");
		shell.pack();
		shell.open();
		
		while (!shell.isDisposed()) {
		        if (!display.readAndDispatch()){
		               display.sleep();
		         }
		}
		display.dispose();
		
		
	}

	public static void main(String[] args) {
		new TestLayout();
	}

}
