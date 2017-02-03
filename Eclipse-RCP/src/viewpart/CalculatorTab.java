package viewpart;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class CalculatorTab extends ViewPart {
    public static final String ID = "eclipsercp.calculatortab";

    public CalculatorTab() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        //new CalculatorView(parent, SWT.NONE);
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }

}
