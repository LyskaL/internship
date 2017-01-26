package llyska.events.table;

import java.util.Set;

/**
 * Interface allows to generate event of change state table
 * and to send it all listeners.
 *
 * @author Lyska Lyudmila
 *
 */
public interface TableEventGenerator {
    /**
     * Returns a list of listeners that signed for the event.
     *
     * @return list of listeners
     */
    Set<TableEventListener> getListeners();

    /**
     * Adds listener in a list of listeners.
     *
     * @param listener
     */
    void addTableEventListener(TableEventListener listener);

    /**
     * Removes listener from a list of listeners.
     *
     * @param listener
     */
    void removeTableEventListener(TableEventListener listener);
}
