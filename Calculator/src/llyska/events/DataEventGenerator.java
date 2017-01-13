package llyska.events;

import java.util.Set;

public interface DataEventGenerator {
	void addDataEventListener(DataEventListener listener);
	void removeDataEventListener(DataEventListener listener);
	Set<DataEventListener> getListeners();
}