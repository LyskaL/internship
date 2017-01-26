package llyska.events.state;

/**
 * The class event arising when the state changes.
 *
 * @author Lyska Lyudmila
 *
 */
public class ChangeStateEvent {
    /**
     * Selected row in table.
     */
    public static final int TABLE_SELECTED = 1;

    /**
     * Filled text fields on form.
     */
    public static final int FORM_FILLED = 1<<2;

    /**
     * Variable stores a state in a bits.
     */
    private final int _state;

    public ChangeStateEvent(int state) {
        super();
        _state = state;
    }

    /**
     * Check to event state equals state transmitted or not.
     *
     * @param state for checking
     * @return true - if equal, false - if not
     */
    public boolean checkState(int state) {
        return (_state & state) != 0;
    }

}
