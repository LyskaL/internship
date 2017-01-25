package llyska.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import llyska.events.ChangeStateEvent;
import llyska.events.ChangeStateEventGenerator;
import llyska.events.ChangeStateEventListener;

public class StateService implements ChangeStateEventGenerator {

    private static StateService _stateService;
    private final Set<ChangeStateEventListener> _listeners;

    static {
        _stateService = new StateService();
    }

    private int _state = 0;

    private StateService() {
        _listeners = new HashSet<>();
    }

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

    public void runEvent() {
        for (ChangeStateEventListener listener : _listeners) {
            listener.handleEvent(new ChangeStateEvent(_state));
        }
    }

    public int getState() {
        return _state;
    }

    public void enableState(int state) {
        _state |= state;
    }

    public void disableState(int stateButton) {
        _state &= ~stateButton;
    }

}
