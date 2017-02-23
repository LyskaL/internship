package studentsrcp;

import org.eclipse.core.expressions.PropertyTester;

public class NewTester extends PropertyTester {

    public NewTester() {
        super();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        System.out.println("NewTester");

        return true;
    }

}
