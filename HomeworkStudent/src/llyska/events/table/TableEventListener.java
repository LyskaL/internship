package llyska.events.table;

import llyska.events.state.ChangeStateEvent;

/**
 * The interface allows state changing events handling of table
 * in which it was signed in implementation.
 *
 * @author Lyska Lyudmila
 */
public interface TableEventListener {

    /**
     * Handles event
     *
     * @param e - event to handle
     */
    void tableEvent(ChangeStateEvent e);
}
