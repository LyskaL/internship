package llyska.listeners;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.form.FormEvent;
import llyska.services.FormEventService;

public class SaveButtonListener implements Listener {
    private final FormEventService _formService = FormEventService.getInstance();;

    @Override
    public void handleEvent(Event event) {
        _formService.setCommand(FormEvent.FORM_SAVE);
        _formService.runEvent();
    }
}
