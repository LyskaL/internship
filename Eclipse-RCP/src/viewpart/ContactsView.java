package viewpart;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import entities.GroupUsers;
import entities.User;
import entities.User.Gender;
import treeprovider.LabelContentProvider;
import treeprovider.TreeContentProvider;

public class ContactsView extends ViewPart {

    public static final String ID = "Eclipse-RCP.viewpart.contacts";

    private TreeViewer _treeViewer;
    private GroupUsers _root;

    public ContactsView() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        _treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);

        _root = new GroupUsers("Contacts", null);
        GroupUsers friendsGroup = new GroupUsers("Friends", _root);
        friendsGroup.addUser(new User("aliz", "gmail.com", "Alize", Gender.FEMALE));
        friendsGroup.addUser(new User("_ssid", "mail.ru", "Sid", Gender.MALE));
        friendsGroup.addUser(new User("max", "yandex.ru", "Maxim", Gender.MALE));
        GroupUsers familyGroup = new GroupUsers("Family", _root);
        familyGroup.addUser(new User("sye", "gmail.com", "Sye", Gender.FEMALE));
        familyGroup.addUser(new User("kost", "mail.ru", "Konstantin", Gender.FEMALE));
        GroupUsers otherGroup = new GroupUsers("Other", _root);
        otherGroup.addUser(new User("den", "mail.ru", "Denis", Gender.MALE));

        _root.addSubGroup(familyGroup);
        _root.addSubGroup(friendsGroup);
        _root.addSubGroup(otherGroup);

        _treeViewer.setContentProvider(new TreeContentProvider());
        _treeViewer.setInput(_root);
        _treeViewer.setLabelProvider(new LabelContentProvider());
        getSite().setSelectionProvider(_treeViewer);
        _treeViewer.getTree().setLinesVisible(true);
    }

    @Override
    public void setFocus() {
        _treeViewer.getControl().setFocus();
    }

}
