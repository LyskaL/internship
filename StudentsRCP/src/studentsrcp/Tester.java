package studentsrcp;

import org.eclipse.core.expressions.PropertyTester;

import events.state.StateForm;
import services.StateService;

public class Tester extends PropertyTester {

    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService = StateService.getInstance();

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (_stateService.checkState(StateForm.FILLED)) {
            return true;
        }
        return false;
    }

}
