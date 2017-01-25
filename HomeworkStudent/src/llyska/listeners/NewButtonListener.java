package llyska.listeners;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.entities.Student;
import llyska.events.state.ChangeStateEvent;
import llyska.interfaces.FormView;
import llyska.services.StateService;
import llyska.services.TableService;
import llyska.util.Constants;

public class NewButtonListener implements Listener {
    private final FormView _textPanel;
    private final StateService _stateService;
    private final TableService _service = Constants.TABLE_SERVICE;

    public NewButtonListener(Composite textPanel) {
        _textPanel = (FormView)textPanel;
        _stateService = StateService.getInstance();
    }

    @Override
    public void handleEvent(Event event) {
        Student student = new Student(_textPanel.getNameField().getText(),
                                      _textPanel.getNumberField().getText(),
                                      _textPanel.getCheckButton().getSelection());
        _service.addStudent(student);

        //TODO
        _textPanel.getNameField().setText("");
        _textPanel.getNumberField().setText("");
        _textPanel.getCheckButton().setSelection(false);
        _stateService.disableState(ChangeStateEvent.FORM_FILLED);
        _stateService.runEvent();
    }

}
