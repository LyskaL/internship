package llyska.events.form;

/**
 * The interface allow handles event of form in which it was signed in implementation.
 *
 * @author Lyska Lyudmila
 *
 */
public interface FormEventListener {
    /**
     * Handles event
     *
     * @param e - arose event
     */
    void formEvent(FormEvent e);
}
