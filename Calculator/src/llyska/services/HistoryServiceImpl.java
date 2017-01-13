package llyska.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import llyska.events.*;

class HistoryServiceImpl implements HistoryService, DataEventGenerator {
	private List<String> _history;
	private Set<DataEventListener> _listeners;

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
		notifyListeners();
	}

	@Override
	public void removeItem(int index) {
		_history.remove(index);
	}

	@Override
	public void removeItem(int[] indexes) {
		for (int i = indexes.length-1; i >= 0; i--) {
			_history.remove(indexes[i]);
		}
	}

	@Override
	public void clean() {
		_history.clear();
	}

	@Override
	public void addDataEventListener(DataEventListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeDataEventListener(DataEventListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<DataEventListener> getListeners() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void notifyListeners() {
		for (DataEventListener listener: _listeners) {
			listener.handleEvent(null);
		}
	}
}
