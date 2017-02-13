package treeprovider;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;

import entities.GroupUsers;

public class TreeContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        GroupUsers groupUsers = (GroupUsers) parentElement;
        ArrayList children = new ArrayList();
        children.addAll(groupUsers.getAllSubGroups());
        children.addAll(groupUsers.getAllUsers());

        if (children.size() == 0) {
            return new Object[0];
        }

        return children.toArray();
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof GroupUsers) {
            return ((GroupUsers) element).getParent();
        }

        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof GroupUsers) {
            GroupUsers group = (GroupUsers) element;

            return group.hasSubGroups() || group.hasUsers();
        }

        return false;
    }

    @Override
    public void dispose() {
        ITreeContentProvider.super.dispose();
    }

}
