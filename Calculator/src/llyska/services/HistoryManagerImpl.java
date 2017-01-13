package llyska.services;

import org.eclipse.swt.widgets.*;

import llyska.interfaces.HistoryView;
import llyska.util.Constants;

public class HistoryManagerImpl implements HistoryManager {
	private HistoryService _historiService;
	private HistoryView _historyView;
	
	public HistoryManagerImpl() {
		Shell shell = new Shell(Constants.DISPLAY_OUR_PROGRAM);
		_historiService = new HistoryServiceImpl();
		_historyView = new HistoryView(shell);
	}
	
	@Override
	public void clean() {
		_historiService.clean();
		//change HistoryView
	}

	@Override
	public void remove(int index) {
		_historiService.removeItem(index);
		//change HistoryView
	}

	@Override
	public Composite getCompositeToHistoryView() {
		return _historyView;
	}

	@Override
	public void setParentToHistoryView(Composite parent) {
		_historyView.setParent(parent);
	}
	
	
}
