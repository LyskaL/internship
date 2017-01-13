package llyska.interfaces;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import llyska.services.*;

public class HistoryView extends Composite{
	private List _history;
	private Button _clean;
	private Button _remove;

	public HistoryView(Composite parent) {
		super(parent, SWT.BORDER);
		setLayout(new GridLayout(1, true));
		
		_history = new List(this, SWT.V_SCROLL | SWT.BORDER | SWT.SINGLE);
		_history.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_history.setBackground(getBackground());
		for (int i = 0; i < 10; i++) {
			_history.add(""+i);
		}
		
		_history.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				_remove.setEnabled(true);
			}
		});
		
		Composite buttonsPanel = new Composite(this, SWT.NONE);
		buttonsPanel.setLayout(new GridLayout(2, true));
		
		_clean = new Button(buttonsPanel, SWT.NONE);
		_clean.setText("Clean");
		_clean.setEnabled(false);
		_clean.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		_clean.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ServiceProvider.getService(HistoryManager.class).clean();
				_history.removeAll();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		_remove = new Button(buttonsPanel, SWT.NONE);
		_remove.setText("Remove");
		_remove.setEnabled(false);
		_remove.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		_remove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selection = _history.getSelectionIndex();
				ServiceProvider.getService(HistoryManager.class).remove(selection);
				_history.remove(selection);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	}
	
	

}
