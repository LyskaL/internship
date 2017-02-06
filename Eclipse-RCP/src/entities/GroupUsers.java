package entities;

import java.util.ArrayList;
import java.util.List;

public class GroupUsers {
    private String _nameGroup;
    private final GroupUsers _parent;
    private final List<User> _users;
    private final List<GroupUsers> _groups;

    public GroupUsers(String name, GroupUsers parent) {
        setNameGroup(name);
        _parent = parent;
        _users = new ArrayList<>();
        _groups = new ArrayList<>();
    }

    public String getNameGroup() {
        return _nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        _nameGroup = nameGroup;
    }

    public void addUser(User user) {
        if(user != null) {
            _users.add(user);
        }
    }

    public void removeUser(User user) {
        if(user != null) {
            _users.remove(user);
        }
    }

    public User getUser(int index) {
        if(index >= 0 && index < sizeUsers()) {
            return _users.get(index);
        }
        return null; // so bad!!
    }

    public int sizeUsers() {
        return _users.size();
    }

    public GroupUsers getParent() {
        return _parent;
    }

    public void addGroup(GroupUsers group) {
        if(group != null) {
            _groups.add(group);
        }
    }

    public void removeGroup(GroupUsers group) {
        if(group != null) {
            _groups.remove(group);
        }
    }

    public GroupUsers getGroup(int index) {
        if(index >= 0 && index < _groups.size()) {
            return _groups.get(index);
        }
        return null; // so bad!!
    }

    public List<GroupUsers> getAllGroups() {
        return _groups;
    }

    public List<User> getAllUsers() {
        return _users;
    }

    public boolean hasGroups() {
        return _groups.size() > 0;
     }

     public boolean hasUsers() {
        return _users.size() > 0;
     }

     @Override
     public String toString() {
        return getNameGroup();
     }
}
