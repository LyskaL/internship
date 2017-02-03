package services;

import java.util.List;

import events.DataEventGenerator;

public interface HistoryService extends DataEventGenerator {
    List<String> getHistory();
    String getItem(int index);
    void addItem(String result);
    void removeItem(int index);
    void removeItems(int[] indexes);
    void clean();
}
