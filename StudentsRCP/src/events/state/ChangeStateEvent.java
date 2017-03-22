package events.state;

import services.StateForm;

/**
 * Event arising when the state changes.
 *
 * @author Lyska Lyudmila
 */
public class ChangeStateEvent {

    /**
     * Stores a state of form
     *
     * Supported:
     * <ul>
     *     <li>StateForm.EMPTY</li>
     *     <li>StateForm.FILLED</li>
     * </ul>
     */
    private final StateForm _state;

    public ChangeStateEvent(StateForm state) {
        super();
        _state = state;
    }

    /**
     * Checks that event state is equal to state passed in arg.
     *
     * @param state for checking
     * @return true - if equal, false - if not
     */
    public boolean checkState(StateForm state) {
        return _state.equals(state);
    }

}
