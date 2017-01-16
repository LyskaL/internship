package llyska.interfaces;

import org.eclipse.swt.*; 
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import llyska.services.*;

public class CalculatorView extends Composite {
	private Button _calculateButton;
	private Button _checkButton;
	private Text _fromNumber;
	private Combo _sign;
	private Text _toNumber;
	private Text _resultText;
	
	private boolean _timer = false;
	private MyTimer thread;
	
	private static final int KEY_CODE_ENTER = 13;
	
	public CalculatorView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.VERTICAL));
		Composite dataPanel = new Composite(this, SWT.NONE);
		dataPanel.setLayout(new GridLayout(3, true));

		_fromNumber = new Text(dataPanel, SWT.BORDER);
		_fromNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		_fromNumber.addKeyListener(new EnterListener());

		_sign = new Combo(dataPanel, SWT.READ_ONLY);
		_sign.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		_sign.add(" + ");
		_sign.add(" - ");
		_sign.add(" / ");
		_sign.add(" * ");
		_sign.setText(" + ");

		_toNumber = new Text(dataPanel, SWT.BORDER);
		_toNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		_toNumber.addKeyListener(new EnterListener());
		
		Composite buttonPanel = new Composite(this, SWT.NONE);
		buttonPanel.setLayout(new GridLayout(2, true));
		setupButtonPanel(buttonPanel);
		
		_sign.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(_checkButton.getSelection()) {
					count();
					saveInHistory("" + _resultText);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

		Composite resultPanel = new Composite(this, SWT.NONE);
		resultPanel.setLayout(new GridLayout(2, true));
		setupResultPanel(resultPanel);
	}
	
	private void setupResultPanel(Composite resultPanel) {
		Label result = new Label(resultPanel, SWT.NONE);
		result.setText("Result: ");
		result.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
		
		_resultText = new Text(resultPanel, SWT.RIGHT | SWT.BORDER);
		_resultText.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
		_resultText.setEnabled(false);
	}

	private void setupButtonPanel(Composite buttonPanel) {
		_checkButton = new Button(buttonPanel, SWT.CHECK);
		_checkButton.setText("Calculate on the fly");
		_checkButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true));
		_checkButton.setSelection(true);
		_checkButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (_checkButton.getSelection()) {
					_calculateButton.setEnabled(false);
				} else {
					_calculateButton.setEnabled(true);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		_calculateButton = new Button(buttonPanel, SWT.NONE);
		_calculateButton.setText("Calculate");
		_calculateButton.setEnabled(false);
		_calculateButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true));
		_calculateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count();
				saveInHistory("" + _resultText);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private boolean count() {
		if(_fromNumber.getCharCount() > 0 && _toNumber.getCharCount() > 0) {
			CalculatorService calculator = ServiceProvider.getService(CalculatorService.class);
			try {
				double result = calculator.count(_fromNumber.getText(), _toNumber.getText(), _sign.getText().charAt(1));
				_resultText.setText("" + result);
			} catch (IllegalArgumentException e) {
				_resultText.setText("" + e.getMessage());
				saveInHistory("" + e.getMessage());
			}
			return true;
		} else {
			return false;
		}
	}
	
	private void saveInHistory(String item) {
		HistoryManager historyTab = ServiceProvider.getService(HistoryManager.class);
		historyTab.addItem(item);
	}
	
	class EnterListener implements KeyListener {
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.keyCode == KEY_CODE_ENTER) {
				count();
			} else {
				if(count()) {
					System.out.println("до старта: " + _resultText.getText());
					if(!_timer) {
						thread = new MyTimer(2000);
						thread.start();
						_timer = true;
					} else {
						thread.updateTime();
					}
				}
			}	
		}
		@Override
		public void keyPressed(KeyEvent e) {}
	}
	
	private class MyTimer extends Thread {
		private int _delay;
		private long _runTime;

		public MyTimer(int seconds) {
			_delay = seconds;
		}
		
		public void updateTime() {
			_runTime = System.currentTimeMillis();
		}
		
		@Override
		public void run() {
			try {
				_runTime = System.currentTimeMillis();
				while (System.currentTimeMillis() < _runTime + _delay) {
					Thread.sleep(100);
				}
				System.out.println("Thread name: " + this.getClass());
				llyska.util.Constants.DISPLAY.asyncExec(new Runnable() {
					@Override
					public void run() {
						saveInHistory("" + _resultText.getText());  
					}
				});
				_timer = false;
			} catch (InterruptedException e) {
				System.out.println("Just exiting...");
			}
		}
	}
	   
}