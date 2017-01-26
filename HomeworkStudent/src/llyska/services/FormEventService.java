package llyska.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import llyska.events.form.FormEvent;
import llyska.events.form.FormEventGenerator;
import llyska.events.form.FormEventListener;

/**
 * The class is service enabling change a state form.
 * The class implements interface for generating event to change a form.
 *
 * @author Lyska Lyudmila
 */
public class FormEventService implements FormEventGenerator {
    /** A set of listeners that subscribe to events in this class **/
    private final Set<FormEventListener> _listeners;
    /** Service for working with data on form. **/
    private static FormEventService _service;
    /** The command for changing form **/
    private int _command;

    static {
        _service = new FormEventService();
    }

    private FormEventService() {
        _listeners = new HashSet<>();
    }

    /**
     * Gets a link to itself.
     * @return form service
     */
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

    /**
     * Sends event for all listeners.
     */
    public void runEvent() {
        for (FormEventListener listener : _listeners) {
            listener.formEvent(new FormEvent(getCommand()));
        }
    }

    /**
     * Gets command for event.
     * @return command
     */
    public int getCommand() {
        return _command;
    }

    /**
     * Sets command for generate event.
     * @param command
     */
    public void setCommand(int command) {
        _command = command;
    }
}
