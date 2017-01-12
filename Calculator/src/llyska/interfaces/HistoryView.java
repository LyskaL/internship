package llyska.interfaces;

import org.eclipse.swt.*; 
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class HistoryView extends Composite{
	private List _history;

	public HistoryView(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout());
		_history = new List(this, SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
		_history.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_history.setBackground(getBackground());
		
		
	}

}
