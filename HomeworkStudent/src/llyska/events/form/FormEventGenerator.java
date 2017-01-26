package llyska.events.form;

import java.util.Set;

/**
 * Interface allows to generate event of form and to send it all listeners.
 *
 * @author Lyska Lyudmila
 *
 */
public interface FormEventGenerator {
    /**
     * Returns a list of listeners that signed for the event.
     *
     * @return list of listeners
     */
    Set<FormEventListener> getListeners();

    /**
     * Adds listener in a list of listeners.
     *
     * @param listener
     */
    void addTableEventListener(FormEventListener listener);

    /**
     * Removes listener from a list of listeners.
     *
     * @param listener
     */
    void removeTableEventListener(FormEventListener listener);
}
