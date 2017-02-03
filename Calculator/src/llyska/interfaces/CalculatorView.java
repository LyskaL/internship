package llyska.interfaces;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import llyska.services.CalculatorService;
import llyska.services.HistoryManager;
import llyska.services.ServiceProvider;
import llyska.util.Constants;

public class CalculatorView extends Composite {
	private Button _calculateButton;
	private Button _checkButton;
	private final Text _fromNumber;
	private final Combo _sign;
	private final Text _toNumber;
	private TextWithBorder _resultText;
	private final Timer _timer = new Timer(2000);

	private final HistoryManager _historyTab = ServiceProvider.getService(HistoryManager.class);
	private final CalculatorService _calculator = ServiceProvider.getService(CalculatorService.class);

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
		_resultText.setBackground(_toNumber.getBackground());
	}

	private void setupButtonPanel(Composite buttonPanel) {
		_checkButton = new Button(buttonPanel, SWT.CHECK);
		_checkButton.setText("Calculate on the fly");
		_checkButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true));
		_checkButton.setSelection(true);
		_checkButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_calculateButton.setEnabled(!_checkButton.getSelection());
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
				if(count()){
					_resultText.redrawBorder(Constants.GREEN);
					saveInHistory("" + _resultText.getText());
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	}

	private boolean count() {
		if (_fromNumber.getCharCount() == 0 || _toNumber.getCharCount() == 0) {
			return false;
		}
		try {
			double result = _calculator.count(_fromNumber.getText(), _toNumber.getText(), _sign.getText().charAt(1));
			_resultText.setText(String.valueOf(result));
		} catch (IllegalArgumentException e) {
			_resultText.setText(e.getMessage());
			saveInHistory(e.getMessage());
			_resultText.redrawBorder(Constants.RED);
			return false;
		}
		return true;
	}

	private void saveInHistory(String item) {
		_historyTab.addItem(item);
	}

	class EnterListener implements KeyListener {
		@Override
		public void keyReleased(KeyEvent e) {
			if (!_checkButton.getSelection()) {
				return;
			}
			if (count() && e.keyCode != KEY_CODE_ENTER) {
				if (_timer.isStartTime()) {
					_timer.updateTime();
				} else {
					_timer.setStartedTime(true);
					_resultText.redrawBorder(Constants.YELLOW);
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {}
	}

	private class Timer extends Thread {
		private final int _delay;
		private long _runTime;

		private boolean _startTime;

		public Timer(int seconds) {
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
						_resultText.redrawBorder(Constants.GREEN);
					}
				});
			} catch (InterruptedException e) {
				System.out.println("Just exiting...");
			}
		}
	}
}