package treeprovider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import entities.GroupUsers;
import entities.User;

public class LabelContentProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof GroupUsers) {
            return ((GroupUsers) element).getNameGroup();
        } else if (element instanceof User) {
            return ((User) element).toString();
        }
        return "" + element;
    }

    @Override
    public Image getImage(Object element) {

        //URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/user_icon.png");
        //ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);

        if (element instanceof GroupUsers) {
            return ((GroupUsers) element).getImage();
        } else if (element instanceof User) {
            return ((User) element).getImage();
        }
        return null;
    }

}