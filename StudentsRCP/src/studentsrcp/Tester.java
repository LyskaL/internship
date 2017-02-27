package studentsrcp;

import org.eclipse.core.expressions.PropertyTester;

import events.state.ChangeStateEvent;
import services.StateService;

public class Tester extends PropertyTester {

    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService = StateService.getInstance();

    public Tester() {
        super();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        System.out.println("Save Tester");
        if ((_stateService.getState() & ChangeStateEvent.FORM_FILLED) != 0) {
            return true;
        }
        return false;
    }

}
