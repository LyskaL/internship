package llyska.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Interface {
	private Shell shell;
	private CTabFolder folder;
	private Button calculateButton;
	private Button chackButton;

	private Text fromNumber;
	private Combo combo;
	private Text toNumber;
	private Text resultText;
	private Label history;
	
	private static final String REGEX_FOR_NUMBER = "^[+\\-]?\\d+(?:\\.\\d+)?$";

	public Interface() {
		Display display = new Display();
		shell = new Shell(display);
		shell.setText("Calculator");
		shell.setLayout(new GridLayout(1, true));

		final int width = 300;
		final int height = 400;
		Monitor monitor = display.getPrimaryMonitor();
		int x = (monitor.getBounds().width / 2) - width / 2;
		int y = (monitor.getBounds().height / 2) - height / 2;
		shell.setBounds(x, y, width, height);

		setupTabs();
		setupCalculatorPanel();
		setupHistoryPanel();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private void setupHistoryPanel() {
		CTabItem historyTab = folder.getItem(1);
		Composite historyPanel = new Composite(folder, SWT.NONE);
		historyPanel.setLayout(new FillLayout(SWT.VERTICAL));
		
		history = new Label(historyPanel, SWT.NONE);
		historyTab.setControl(historyPanel);
	}

	private void setupCalculatorPanel() {
		CTabItem calculatorTab = folder.getItem(0);

		Composite calculatorPanel = new Composite(folder, SWT.NONE);
		calculatorPanel.setLayout(new FillLayout(SWT.VERTICAL));

		Composite dataPanel = new Composite(calculatorPanel, SWT.NONE);
		dataPanel.setLayout(new GridLayout(3, true));

		fromNumber = new Text(dataPanel, SWT.BORDER);
		fromNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		fromNumber.addFocusListener(new TextListener());

		combo = new Combo(dataPanel, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		combo.add(" + ");
		combo.add(" - ");
		combo.add(" / ");
		combo.add(" * ");
		combo.setText(" + ");

		toNumber = new Text(dataPanel, SWT.BORDER);
		toNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		toNumber.addFocusListener(new TextListener());

		Composite buttonPanel = new Composite(calculatorPanel, SWT.NONE);
		buttonPanel.setLayout(new GridLayout(2, true));

		chackButton = new Button(buttonPanel, SWT.CHECK);
		chackButton.setText("Calculate on the fly");
		chackButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true));
		chackButton.setSelection(true);
		chackButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (chackButton.getSelection()) {
					calculateButton.setEnabled(false);
				} else {
					calculateButton.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(chackButton.getSelection()) {
					count();
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

		calculateButton = new Button(buttonPanel, SWT.NONE);
		calculateButton.setText("Calculate");
		calculateButton.setEnabled(false);
		calculateButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true));

		Composite resultPanel = new Composite(calculatorPanel, SWT.NONE);
		resultPanel.setLayout(new GridLayout(2, true));
		Label result = new Label(resultPanel, SWT.NONE);
		result.setText("Result: ");
		result.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
		
		resultText = new Text(resultPanel, SWT.RIGHT | SWT.BORDER);
		resultText.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));

		calculateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		calculatorTab.setControl(calculatorPanel);
	}

	private void setupTabs() {
		folder = new CTabFolder(shell, SWT.NONE);
		folder.setBackground(shell.getBackground());
		folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		CTabItem calculator = new CTabItem(folder, SWT.NONE);
		calculator.setText("Calculator");

		CTabItem history = new CTabItem(folder, SWT.NONE);
		history.setText("History");

		ToolBar toolBar = new ToolBar(shell, SWT.BORDER);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true));
	}
	
	//TODO
	private void count(){
		if(fromNumber.getCharCount() > 0 && toNumber.getCharCount() > 0) {
			double firstNumber = 0.0;
			double secondNumber = 0.0;
			if(isValidString(fromNumber.getText()) && isValidString(toNumber.getText())) {
				firstNumber =  Double.parseDouble(fromNumber.getText());
				secondNumber =  Double.parseDouble(toNumber.getText());
			} else {
				resultText.setText("not correct number");
				return;
			}
			char symbol = combo.getText().charAt(1);
			
			double result = 0.0;
			switch(symbol){
				case '+':
					result = firstNumber + secondNumber;
					break;
				case '-':
					result = firstNumber - secondNumber;
					break;
				case '*':
					result = firstNumber * secondNumber;
					break;
				case '/':
					if(firstNumber == 0.0) {
						resultText.setText("zero cannot be split");
					}
					result = firstNumber/secondNumber;
					break;
			}
			resultText.setText(""+result);
			history.setText(history.getText()+("\n"+result));
		}
	}
	
	private boolean isValidString(String string){
		Pattern p = Pattern.compile(REGEX_FOR_NUMBER);
		Matcher m = p.matcher(string);
		return m.matches();
	}

	public static void main(String[] args) {
		new Interface();
	}

	class TextListener implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {}
		@Override
		public void focusLost(FocusEvent e) {
			if (chackButton.getSelection()) {
				count();
			}
		}
	}
}
