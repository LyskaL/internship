package llyska.interfaces;


import org.eclipse.swt.*; 
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class CalculatorView extends Composite {
	private Button _calculateButton;
	private Button _checkButton;
	private Text _fromNumber;
	private Combo _sign;
	private Text _toNumber;
	private Text _resultText;
	
	public CalculatorView(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.VERTICAL));
		Composite dataPanel = new Composite(this, SWT.NONE);
		dataPanel.setLayout(new GridLayout(3, true));

		_fromNumber = new Text(dataPanel, SWT.BORDER);
		_fromNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		//_fromNumber.addFocusListener(new TextListener());

		_sign = new Combo(dataPanel, SWT.READ_ONLY);
		_sign.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		_sign.add(" + ");
		_sign.add(" - ");
		_sign.add(" / ");
		_sign.add(" * ");
		_sign.setText(" + ");

		_toNumber = new Text(dataPanel, SWT.BORDER);
		_toNumber.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		//_toNumber.addFocusListener(new TextListener());

		Composite buttonPanel = new Composite(this, SWT.NONE);
		buttonPanel.setLayout(new GridLayout(2, true));
		
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
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		_sign.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(_checkButton.getSelection()) {
					//count();
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

		_calculateButton = new Button(buttonPanel, SWT.NONE);
		_calculateButton.setText("Calculate");
		_calculateButton.setEnabled(false);
		_calculateButton.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true));

		Composite resultPanel = new Composite(this, SWT.NONE);
		resultPanel.setLayout(new GridLayout(2, true));
		Label result = new Label(resultPanel, SWT.NONE);
		result.setText("Result: ");
		result.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));
		
		_resultText = new Text(resultPanel, SWT.RIGHT | SWT.BORDER);
		_resultText.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true));

		_calculateButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//count();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	
}