package studentsrcp;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.StructuredSelection;

import events.state.ChangeStateEvent;
import services.StateService;

public class DeleteTester extends PropertyTester {

    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService = StateService.getInstance();

    public DeleteTester() {
        super();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof StructuredSelection) {
            if (!((StructuredSelection) receiver).isEmpty()) {
                _stateService.enableState(ChangeStateEvent.TABLE_SELECTED);
                return true;
            }
        }
        _stateService.disableState(ChangeStateEvent.TABLE_SELECTED);
        return false;
    }
}
