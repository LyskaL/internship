package llyska.events.state;

/**
 * The interface allow handles event of change state in which
 * it was signed in implementation.
 *
 * @author Lyska Lyudmila
 *
 */
public interface ChangeStateEventListener {
    /**
     * Handles event
     *
     * @param e - arose event
     */
    void handleEvent(ChangeStateEvent e);
}