package events.state;

/**
 * The interface allows state change event handling.
 *
 * @author Lyska Lyudmila
 */
public interface ChangeStateEventListener {

    /**
     * Handles event
     *
     * @param e - event to handle
     */
    void handleEvent(ChangeStateEvent event);
}
