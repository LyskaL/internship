package entities;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import entities.User.GroupType;
import viewpart.Constants;

public class GroupUsers {
    private String _nameGroup;
    private final GroupUsers _parent;
    private final List<User> _users;
    private final List<GroupUsers> _groups;
    private Image _image;


    public GroupUsers(List<User> users, String name, GroupUsers parent) {
        this(name, parent);
        for (GroupType element : GroupType.values()) {
            _groups.add(new GroupUsers(element.toString(), this));
        }
        addUsers(users);

    }
    public GroupUsers(String name, GroupUsers parent) {
        setNameGroup(name);
        _parent = parent;
        _users = new ArrayList<>();
        _groups = new ArrayList<>();

        URL url = Platform.getBundle("Eclipse-RCP").getEntry("icons/" + name + "_icon.png");
        ImageDescriptor image = ImageDescriptor.createFromURL(url);
        setImage(new Image(Constants.DISPLAY, image.getImageData()));
    }

    private void setImage(Image image) {
        _image = image;
    }

    public Image getImage() {
        return _image;
    }

    public String getNameGroup() {
        return _nameGroup;
    }

    public String getFullNameGroup() {
        return _nameGroup + " (0/" + _users.size() + ")";
    }

    public void setNameGroup(String nameGroup) {
        _nameGroup = nameGroup;
    }


    public void addUser(User user) {
        addUser(user.getGroupType(), user);
    }

    private void addUser(GroupType type, User user) {
        if (user != null) {
            getSubGroup(type).getAllUsers().add(user);
        }
    }

    private void addUsers(List<User> users) {
        for (User user : users) {
            addUser(user.getGroupType(), user);
        }
    }

    public void removeUser(User user) {
        removeUser(user.getGroupType(), user);
    }

    private void removeUser(GroupType type, User user) {
        if (user != null) {
            getSubGroup(type).getAllUsers().remove(user);
        }
    }

//    public User getUser(int index) {
//        if (index >= 0 && index < sizeUsers()) {
//            return _users.get(index);
//        }
//        return null; // so bad!!
//    }

    public int sizeUsers() {
        return _users.size();
    }

    public GroupUsers getParent() {
        return _parent;
    }

//    public void addSubGroup(GroupUsers group) {
//        if (group != null) {
//            _groups.add(group);
//        }
//    }

//    public void removeSubGroup(GroupUsers group) {
//        if (group != null) {
//            _groups.remove(group);
//        }
//    }

    public GroupUsers getSubGroup(int index) {
        if (index >= 0 && index < _groups.size()) {
            return _groups.get(index);
        }
        return null; // so bad!!
    }

    public GroupUsers getSubGroup(GroupType type) {
        for (GroupUsers groupUsers : _groups) {
            if(groupUsers.getNameGroup().equals(type.toString())) {
                return groupUsers;
            }
        }

        return null; // so bad!!
    }

    public List<GroupUsers> getAllSubGroups() {
        return _groups;
    }

    public List<User> getAllUsers() {
        return _users;
    }

    public boolean hasSubGroups() {
        return _groups.size() > 0;
    }

    public boolean hasUsers() {
        return _users.size() > 0;
    }

    @Override
    public String toString() {
        return getFullNameGroup();
    }
}
