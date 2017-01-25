package llyska.listeners;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.events.state.ChangeStateEvent;
import llyska.interfaces.FormView;
import llyska.services.StateService;

public class CancelButtonListener implements Listener {
    private final FormView _textPanel;
    private final StateService _stateService;

    public CancelButtonListener(Composite textPanel) {
        _textPanel = (FormView)textPanel;
        _stateService = StateService.getInstance();
    }
    @Override
    public void handleEvent(Event event) {
        _textPanel.getNameField().setText("");
        _textPanel.getNumberField().setText("");
        _textPanel.getCheckButton().setSelection(false);
        _stateService.disableState(ChangeStateEvent.FORM_FILLED);
        _stateService.runEvent();
    }

}
