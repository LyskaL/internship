package events.form;

import java.util.Set;

/**
 * Interface allows to generate event of form and to send it to all listeners.
 *
 * @author Lyska Lyudmila
 */
public interface FormEventGenerator {
    /**
     * Returns a set of listeners that are signed for the event.
     *
     * @return set of listeners
     */
    Set<FormEventListener> getListeners();

    /**
     * Adds listener to a set of listeners.
     *
     * @param listener
     */
    void addTableEventListener(FormEventListener listener);

    /**
     * Removes listener from a set of listeners.
     *
     * @param listener
     */
    void removeTableEventListener(FormEventListener listener);
}