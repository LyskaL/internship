package table.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

public class CheckButtonProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        return null;
    }

    @Override
    public Image getImage(Object element) {
//        if (((Student) element).isSwtDone()) {
//            return Constants.CHECKED;
//        } else {
//            return Constants.UNCHECKED;
//        }
        return null;
    }
}
