package llyska.events.state;

import java.util.Set;

/**
 * Interface allows to generate event of change state and to send it all listeners.
 *
 * @author Lyska Lyudmila
 *
 */
public interface ChangeStateEventGenerator {
    /**
     * Returns a list of listeners that signed for the event.
     *
     * @return list of listeners
     */
    Set<ChangeStateEventListener> getListeners();

    /**
     * Adds listener in a list of listeners.
     *
     * @param listener
     */
    void addDataEventListener(ChangeStateEventListener listener);

    /**
     * Removes listener from a list of listeners.
     *
     * @param listener
     */
    void removeDataEventListener(ChangeStateEventListener listener);
}
