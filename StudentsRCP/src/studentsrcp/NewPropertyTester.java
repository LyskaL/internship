package studentsrcp;

import org.eclipse.core.expressions.PropertyTester;

public class NewPropertyTester extends PropertyTester {
    public static final String PROPERTY_NAMESPACE = "StudentsRCP";
    public static final String PROPERTY_NEW = "new";
    public static final String PROPERTY_SAVE = "save";
    public static final String PROPERTY_DELETE = "delete";
    public static final String PROPERTY_CANCEL = "cancel";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        System.out.println("test");
        if (PROPERTY_NEW.equals(property)) {
            return true;
        } else if (PROPERTY_SAVE.equals(property)) {
            return true;
        }
        return false;
    }
}
