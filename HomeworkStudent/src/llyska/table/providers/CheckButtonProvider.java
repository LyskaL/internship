package llyska.table.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

import llyska.entities.Student;
import llyska.util.Constants;

/**
 * The class extends ColumnLabelProvider.
 *
 * The class is responsible for display information
 * in cell with check button.
 *
 * @author Lyska Lyudmila
 */
public class CheckButtonProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        return null;
    }

    @Override
    public Image getImage(Object element) {
        if (((Student) element).isSwtDone()) {
            return Constants.CHECKED;
        } else {
            return Constants.UNCHECKED;
        }
    }
}
