package llyska.calculator;

import java.awt.Checkbox;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Interface {
	private Shell shell;
	private CTabFolder folder;
	
	public Interface() {
		Display display = new Display();
		shell = new Shell(display);
		shell.setText("Calculator");
		shell.setLayout(new FillLayout());
		
		final int width = 400;
		final int height = 400;
		Monitor monitor = display.getPrimaryMonitor();
		int x = (monitor.getBounds().width/2)-width/2;
		int y = (monitor.getBounds().height/2)-height/2;
		shell.setBounds(x, y, width, height);
		
		setupTabs();
		//TODO
		setupCalculatorPanel();
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	private void setupCalculatorPanel() {
		CTabItem calculatorTab = folder.getItem(0);
		
		Composite calculatorPanel = new Composite(folder, SWT.NONE);
		calculatorPanel.setLayout(new FillLayout(SWT.VERTICAL));
		
		Composite dataPanel = new Composite(calculatorPanel, SWT.NONE);
		dataPanel.setLayout(new GridLayout(3, true));
		
		Text fromNumber = new Text(dataPanel, SWT.BORDER);
		fromNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		Combo combo = new Combo (dataPanel, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		combo.add(" + ");
		combo.add(" - ");
		combo.add(" / ");
		combo.add(" * ");
		combo.setText(" + ");
		
		Text toNumber = new Text(dataPanel, SWT.BORDER);
		toNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		Composite buttonPanel = new Composite(calculatorPanel, SWT.NONE);
		buttonPanel.setLayout(new GridLayout(2, true));
		
		Button chackButton = new Button(buttonPanel, SWT.CHECK);
		chackButton.setText("Calculate on the fly");
		chackButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
		Button calculateButton = new Button(buttonPanel, SWT.NONE);
		calculateButton.setText("Calculate");
		calculateButton.setEnabled(false);
		calculateButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true));
		
		Composite resultPanel = new Composite(calculatorPanel, SWT.NONE);
		resultPanel.setLayout(new GridLayout(2, true));
		Label result = new Label(resultPanel, SWT.NONE);
		result.setText("Result: ");
		result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		Text resultText = new Text(resultPanel, SWT.BORDER);
		resultText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		calculatorTab.setControl(calculatorPanel);
	}

	private void setupTabs() {
		folder = new CTabFolder(shell, SWT.NONE);
	    folder.setBackground(shell.getBackground());
		
	    CTabItem calculator = new CTabItem(folder, SWT.NONE);
	    calculator.setText("Calculator");
	    
	    CTabItem history = new CTabItem(folder, SWT.NONE);
	    history.setText("History");
	}

	public static void main(String[] args) {
		new Interface();
	}

}
