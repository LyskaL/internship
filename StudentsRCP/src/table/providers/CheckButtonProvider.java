package table.providers;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

import entities.Student;

public class CheckButtonProvider extends ColumnLabelProvider {
    @Override
    public String getText(Object element) {
        return null;
    }

    @Override
    public Image getImage(Object element) {
        if (((Student) element).isSwtDone()) {
            URL url = Platform.getBundle("StudentsRCP").getEntry("icons/CHECKED.png");
            return ImageDescriptor.createFromURL(url).createImage();
        } else {
            URL url = Platform.getBundle("StudentsRCP").getEntry("icons/UNCHECKED.png");
            return ImageDescriptor.createFromURL(url).createImage();
        }
    }
}
