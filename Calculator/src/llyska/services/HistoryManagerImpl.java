package llyska.services;

import org.eclipse.swt.widgets.*;
import llyska.events.*;
import llyska.interfaces.HistoryView;
import llyska.util.Constants;

public class HistoryManagerImpl implements HistoryManager, DataEventListener {
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
	}

	@Override
	public void remove(int index) {
		_historiService.removeItem(index);
	}

	@Override
	public Composite getCompositeToHistoryView() {
		return _historyView;
	}

	@Override
	public void setParentToHistoryView(Composite parent) {
		_historyView.setParent(parent);
	}

	@Override
	public void addItem(String result) {
		_historiService.addItem(result);
	}

	@Override
	public void removeItem(int[] indexes) {
		_historiService.removeItem(indexes);
	}

	@Override
	public void handleEvent(DataEvent e) {
		_historyView.cleanHistory();
		_historyView.createHistory(_historiService.getHistory());
	}
	
	
}
