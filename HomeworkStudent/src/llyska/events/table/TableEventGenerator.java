package llyska.events.table;

import java.util.Set;

/**
 * Interface allows to generate event of table state change
 * and to send it to all listeners.
 *
 * @author Lyska Lyudmila
 */
public interface TableEventGenerator {
    /**
     * Returns a set of listeners that are signed for the event.
     *
     * @return set of listeners
     */
    Set<TableEventListener> getListeners();

    /**
     * Adds listener to a set of listeners.
     *
     * @param listener
     */
    void addTableEventListener(TableEventListener listener);

    /**
     * Removes listener from a set of listeners.
     *
     * @param listener
     */
    void removeTableEventListener(TableEventListener listener);
}
