package llyska.events.form;

/**
 * The interface allows event handling of form in which it was signed in implementation.
 *
 * @author Lyska Lyudmila
 */
public interface FormEventListener {

    /**
     * Handles event
     *
     * @param e - event to handle
     */
    void formEvent(FormEvent e);
}
