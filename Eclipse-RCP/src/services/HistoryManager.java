package services;

import org.eclipse.swt.widgets.Composite;

public interface HistoryManager {
    Composite getCompositeToHistoryView();
    void setParentToHistoryView(Composite parent);

    void clean();
    void remove(int index);
    void removeItems(int[] indexes);
    void addItem(String result);
  }