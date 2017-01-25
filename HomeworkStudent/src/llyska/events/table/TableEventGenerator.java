package llyska.events.table;

import java.util.Set;

public interface TableEventGenerator {
    void addTableEventListener(TableEventListener listener);
    void removeTableEventListener(TableEventListener listener);
    Set<TableEventListener> getListeners();

}
