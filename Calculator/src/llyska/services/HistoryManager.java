package llyska.services;

import org.eclipse.swt.widgets.*;

public interface HistoryManager {
	Composite getCompositeToHistoryView();
	void setParentToHistoryView(Composite parent);
	
	void clean();
	void remove(int index);
	void removeItems(int[] indexes);
	void addItem(String result);
}
