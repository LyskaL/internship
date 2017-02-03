package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import events.DataEventListener;

class HistoryServiceImpl implements HistoryService {
    private final List<String> _history;
    private final Set<DataEventListener> _listeners;

    public HistoryServiceImpl() {
      _history = new ArrayList<>();
      _listeners = new HashSet<>();
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
      notifyListeners();
    }

    @Override
    public void removeItems(int[] indexes) {
      for (int i = indexes.length-1; i >= 0; i--) {
        _history.remove(indexes[i]);
      }
      notifyListeners();
    }

    @Override
    public void clean() {
      _history.clear();
    }

    @Override
    public void addDataEventListener(DataEventListener listener) {
      _listeners.add(listener);
    }

    @Override
    public void removeDataEventListener(DataEventListener listener) {
      _listeners.remove(listener);
    }

    @Override
    public Set<DataEventListener> getListeners() {
      return Collections.unmodifiableSet(_listeners);
    }

    private void notifyListeners() {
      for (DataEventListener listener: _listeners) {
        listener.handleEvent(null);
      }
    }
  }
