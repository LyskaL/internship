package llyska.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import llyska.events.form.FormEvent;
import llyska.events.form.FormEventGenerator;
import llyska.events.form.FormEventListener;

public class FormEventService implements FormEventGenerator {
    private final Set<FormEventListener> _listeners;
    private static FormEventService _service;

    private int _command;

    static {
        _service = new FormEventService();
    }

    public FormEventService() {
        _listeners = new HashSet<>();
    }

    public static FormEventService getInstance() {
        return _service;
    }

    @Override
    public void addTableEventListener(FormEventListener listener) {
        _listeners.add(listener);

    }

    @Override
    public void removeTableEventListener(FormEventListener listener) {
        _listeners.remove(listener);
    }

    @Override
    public Set<FormEventListener> getListeners() {
        return Collections.unmodifiableSet(_listeners);
    }

    public void runEvent() {
        for (FormEventListener listener : _listeners) {
            listener.formEvent(new FormEvent(getCommand()));
        }
    }

    public int getCommand() {
        return _command;
    }

    public void setCommand(int command) {
        _command = command;
    }
}
