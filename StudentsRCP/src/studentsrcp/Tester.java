package studentsrcp;

import org.eclipse.core.expressions.PropertyTester;

import services.StateForm;
import services.StateService;

/**
 * Tester for change state "Save" and "Cancel" button from menu, toolbar.
 *
 * @author Lyska Lyudmila
 *
 */
public class Tester extends PropertyTester {

    /** Service for changing state buttons on form and menu panel **/
    private final StateService _stateService = StateService.getInstance();


    @Override

    /**
     * @retur TRUE - if all the text fields on the panel filled, otherwise - FALSE.
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (_stateService.checkState(StateForm.FILLED)) {
            return true;
        }
        return false;
    }

}
