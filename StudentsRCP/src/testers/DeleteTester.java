package testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.StructuredSelection;

public class DeleteTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof StructuredSelection) {
            if (!((StructuredSelection) receiver).isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
