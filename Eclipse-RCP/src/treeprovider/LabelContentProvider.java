package treeprovider;

import org.eclipse.jface.viewers.LabelProvider;

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

   /* @Override
    public Image getImage(Object element) {

        File dir = new File(".");
        File[] filesList = dir.listFiles();
        for(File f : filesList){

            if(f.isDirectory()) {
                System.out.println(f.getName());
            }
            if(f.isFile()){
                System.out.println(f.getName());
            }
        }

        System.out.println(dir.getAbsolutePath());

        return new Image(Constants.DISPLAY, "/icons/user_icon.png");
    }*/

}