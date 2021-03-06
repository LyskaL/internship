package llyska.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import llyska.events.state.ChangeStateEvent;
import llyska.events.state.ChangeStateEventGenerator;
import llyska.events.state.ChangeStateEventListener;

/**
 *
 * The class is service enabling to change state buttons and menu items.
 * The class implements interface for generating event to change state.
 *
 * @author Lyska Lyudmila
 *
 */
public class StateService implements ChangeStateEventGenerator {

    /** Service for changing state buttons on form and menu panel **/
    private static StateService _stateService;

    /** A set of listeners that subscribe to events in this class **/
    private final Set<ChangeStateEventListener> _listeners;

    static {
        _stateService = new StateService();
    }

    private int _state = 0;

    private StateService() {
        _listeners = new HashSet<>();
    }

    /**
     * Gets a link to itself.
     * @return state service
     */
    public static StateService getInstance() {
        return _stateService;
    }

    @Override
    public void addDataEventListener(ChangeStateEventListener listener) {
        _listeners.add(listener);
    }

    @Override
    public void removeDataEventListener(ChangeStateEventListener listener) {
        _listeners.remove(listener);
    }

    @Override
    public Set<ChangeStateEventListener> getListeners() {
        return Collections.unmodifiableSet(_listeners);
    }

    /**
     * Sends event for all listeners.
     */
    public void runEvent() {
        for (ChangeStateEventListener listener : _listeners) {
            listener.handleEvent(new ChangeStateEvent(_state));
        }
    }

    /**
     * Gets state for event.
     * @return state
     */
    public int getState() {
        return _state;
    }

    /**
     * Enables state for generate event.
     *
     * @param state - that to enable
     */
    public void enableState(int state) {
        _state |= state;
    }

    /**
     * Disables state for generate event.
     *
     * @param stateButton - that to disable
     */
    public void disableState(int stateButton) {
        _state &= ~stateButton;
    }

}
