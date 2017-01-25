package llyska.listeners;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import llyska.entities.Student;
import llyska.interfaces.FormView;
import llyska.services.TableService;
import llyska.util.Constants;

public class NewButtonListener implements Listener {
    private final FormView _textPanel;
    private final TableService _service = Constants.TABLE_SERVICE;

    public NewButtonListener(Composite textPanel) {
        _textPanel = (FormView)textPanel;
    }

    @Override
    public void handleEvent(Event event) {
        Student student = new Student(_textPanel.getNameField().getText(),
                                      _textPanel.getNumberField().getText(),
                                      _textPanel.getCheckButton().getSelection());
        System.out.println(student.toString());
        _service.addStudent(student);
    }

}
