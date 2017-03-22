package testers;

import org.eclipse.core.expressions.PropertyTester;

import services.StateForm;
import services.StateService;

/**
 * Tester for change state "Save" and "Cancel" button from menu, toolbar.
 *
 * @author Lyska Lyudmila
 *
 */
public class StateFormTester extends PropertyTester {

    @Override
    /**
     * @retur TRUE - if all the text fields on the panel filled, otherwise - FALSE.
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (StateService.getInstance().checkState(StateForm.FILLED)) {
            return true;
        }
        return false;
    }

}
