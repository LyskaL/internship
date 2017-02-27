package events.state;

import java.util.Set;

/**
 * Interface allows to generate state changing events and to send it to all listeners.
 *
 * @author Lyska Lyudmila
 */
public interface ChangeStateEventGenerator {
    /**
     * Returns a set of listeners that are signed for the event.
     *
     * @return set of listeners
     */
    Set<ChangeStateEventListener> getListeners();

    /**
     * Adds listener to a set of listeners.
     *
     * @param listener
     */
    void addDataEventListener(ChangeStateEventListener listener);

    /**
     * Removes listener from a set of listeners.
     *
     * @param listener
     */
    void removeDataEventListener(ChangeStateEventListener listener);
}