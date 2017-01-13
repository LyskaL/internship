package llyska.services;

import org.eclipse.swt.widgets.*;

public interface HistoryManager {
	void clean();
	void remove(int index);
	Composite getCompositeToHistoryView();
	void setParentToHistoryView(Composite parent);
}
