package llyska.events.state;

/**
 * Event arising when the state changes.
 *
 * @author Lyska Lyudmila
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
     * Stores a state in a bits.
     *
     * Supported bits:
     * <ul>
     *     <li>ChangeStateEvent.TABLE_SELECTED</li>
     *     <li>ChangeStateEvent.FORM_FILLED</li>
     * </ul>
     */
    private final int _state;


    /**
     * Constructor to create a event.
     *
     * @param state stores a state in a bits.
     *
     * Supported bits:
     * <ul>
     *     <li>ChangeStateEvent.TABLE_SELECTED</li>
     *     <li>ChangeStateEvent.FORM_FILLED</li>
     * </ul>
     */
    public ChangeStateEvent(int state) {
        super();
        _state = state;
    }

    /**
     * Checks that event state is equal to state passed in arg.
     *
     * @param state for checking
     * @return true - if equal, false - if not
     */
    public boolean checkState(int state) {
        return (_state & state) != 0;
    }

}
