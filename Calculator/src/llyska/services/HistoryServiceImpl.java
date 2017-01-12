package llyska.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HistoryServiceImpl implements HistoryService {
	private List<String> _history;

	public HistoryServiceImpl() {
		_history = new ArrayList<>();
	}
	
	@Override
	public List<String> getHistory() {
		return Collections.unmodifiableList(_history);
	}

	@Override
	public String getItem(int index) {
		return _history.get(index);
	}

	@Override
	public void addItem(String result) {
		_history.add(result);
	}

	@Override
	public void removeItem(int index) {
		_history.remove(index);
	}

	@Override
	public void removeItem(int[] indexes) {
		//TODO
	}

	@Override
	public void clear() {
		_history.clear();
	}
	
	
	
	
}
