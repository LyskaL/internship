package events.table;

/**
 * The interface allows state changing events handling of table in which it was signed in implementation.
 *
 * @author Lyska Lyudmila
 */
public interface TableEventListener {

    /**
     * Handles event
     */
    void tableEvent();
}