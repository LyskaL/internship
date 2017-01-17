package llyska.interfaces;

import org.eclipse.swt.*; 
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import llyska.services.*;
import llyska.util.Constants;

public class CalculatorView extends Composite {
	private Button _calculateButton;
	private Button _checkButton;
	private Text _fromNumber;
	private Combo _sign;
	private Text _toNumber;
	private TextWithBorder _resultText;
	
	private MyTimer _timer = new MyTimer(2000);
	
	private static final int KEY_CODE_ENTER = 13;
	
	public CalculatorView(Composite parent, int style) {
		super(parent, style);
		_timer.start();
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
					saveInHistory("" + _resultText.getText());
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
		Composite labelPanel = new Composite(resultPanel, SWT.NONE);
		labelPanel.setLayout(new GridLayout(1, true));
		labelPanel.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
		Label result = new Label(labelPanel, SWT.NONE);
		result.setText("Result: ");
		result.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
		
		_resultText = new TextWithBorder(resultPanel, SWT.NONE);
		_resultText.setEditable(false);
		_resultText.changeStyle(SWT.RIGHT);
		//_resultText.redrawBorder(Constants.GREEN);
	}

	private void setupButtonPanel(Composite buttonPanel) {
		_checkButton = new Button(buttonPanel, SWT.CHECK);
		_checkButton.setText("Calculate on the fly");
		_checkButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
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
		_calculateButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true));
		_calculateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				count();
				saveInHistory("" + _resultText.getText());
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
					if(_timer.isStartTime()) {
						_timer.updateTime();
					} else {
						_timer.setStartedTime(true);
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
		
		private boolean _startTime;

		public MyTimer(int seconds) {
			_delay = seconds;
			_startTime = false;
			setDaemon(true);
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(100);
					if (isStartTime()) {
						countdown();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public boolean isStartTime() {
			return _startTime;
		}

		public void setStartedTime(boolean startedTime) {
			_startTime = startedTime;
		}

		public void updateTime() {
			_runTime = System.currentTimeMillis();
		}
		
		private void countdown() {
			try {
				_runTime = System.currentTimeMillis();
				while (System.currentTimeMillis() < _runTime + _delay) {
					Thread.sleep(100);
				}
				llyska.util.Constants.DISPLAY.asyncExec(new Runnable() {
					@Override
					public void run() {
						setStartedTime(false);
						saveInHistory("" + _resultText.getText());
					}
				});
			} catch (InterruptedException e) {
				System.out.println("Just exiting...");
			}
		}
	}   
}