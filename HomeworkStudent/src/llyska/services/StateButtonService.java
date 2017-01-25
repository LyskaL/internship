package llyska.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import llyska.events.ChangeStateButtonEvent;
import llyska.events.ChangeStateButtonEventGenerator;
import llyska.events.ChangeStateButtonEventListener;

public class StateButtonService implements ChangeStateButtonEventGenerator {

    private static StateButtonService _serviceButton;
    private final Set<ChangeStateButtonEventListener> _listeners;

    static {
        _serviceButton = new StateButtonService();
    }

    private StateButtonService() {
        System.out.println("Создан обьект StateButtonService");
        _listeners = new HashSet<>();
    }

    public static StateButtonService getInstance() {
        return _serviceButton;
    }

    @Override
    public void addDataEventListener(ChangeStateButtonEventListener listener) {
        _listeners.add(listener);
    }

    @Override
    public void removeDataEventListener(ChangeStateButtonEventListener listener) {
        _listeners.remove(listener);
    }

    @Override
    public Set<ChangeStateButtonEventListener> getListeners() {
        return Collections.unmodifiableSet(_listeners);
    }

    public void runEvent() {
        for (ChangeStateButtonEventListener listener: _listeners) {
          listener.handleEvent(ChangeStateButtonEvent.getInstance());
        }
      }

}
