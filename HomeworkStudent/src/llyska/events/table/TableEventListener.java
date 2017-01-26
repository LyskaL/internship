package llyska.events.table;

import llyska.events.state.ChangeStateEvent;

/**
 * The interface allow handles event of change state of table
 * in which it was signed in implementation.
 *
 * @author Lyska Lyudmila
 *
 */
public interface TableEventListener {
    /**
     * Handles event
     *
     * @param e - arose event
     */
    void tableEvent(ChangeStateEvent e);
}
