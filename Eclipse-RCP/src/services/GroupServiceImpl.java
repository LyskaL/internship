package services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import entities.GroupUsers;
import entities.User;
import entities.User.Gender;
import events.GroupChangeListener;

public class GroupServiceImpl implements GroupService {
    private final GroupUsers _group;
    private static GroupServiceImpl _servise;
    private final Set<GroupChangeListener> _listeners;

    static {
        _servise = new GroupServiceImpl();
    }

    private GroupServiceImpl() {
        _listeners = new HashSet<>();

        _group = new GroupUsers("Contacts", null);
        GroupUsers friendsGroup = new GroupUsers("Friends", _group);
        friendsGroup.addUser(new User("aliz", "gmail.com", "Alize", Gender.FEMALE));
        friendsGroup.addUser(new User("_ssid", "mail.ru", "Sid", Gender.MALE));
        friendsGroup.addUser(new User("max", "yandex.ru", "Maxim", Gender.MALE));
        GroupUsers familyGroup = new GroupUsers("Family", _group);
        familyGroup.addUser(new User("sye", "gmail.com", "Sye", Gender.FEMALE));
        familyGroup.addUser(new User("kost", "mail.ru", "Konstantin", Gender.FEMALE));
        GroupUsers otherGroup = new GroupUsers("Other", _group);
        otherGroup.addUser(new User("den", "mail.ru", "Denis", Gender.MALE));

        _group.addSubGroup(familyGroup);
        _group.addSubGroup(friendsGroup);
        _group.addSubGroup(otherGroup);
    }

    public static GroupServiceImpl getInstance() {
        return _servise;
    }

    @Override
    public void addUser(User user, GroupUsers toSubGroup) {
        toSubGroup.addUser(user);
        notifyListeners();
    }

    @Override
    public void addSubGroup(GroupUsers group) {
        _group.addSubGroup(group);
        notifyListeners();
    }

    @Override
    public void removeUser(User user) {
        for (GroupUsers group : _group.getAllSubGroups()) {
            group.removeUser(user);
        }
        notifyListeners();
    }

    @Override
    public void removeSubGroup(GroupUsers group) {
        _group.removeSubGroup(group);
        notifyListeners();
    }

    @Override
    public GroupUsers getGroupRoot() {
        return _group;
    }

    @Override
    public void addDataEventListener(GroupChangeListener listener) {
        _listeners.add(listener);
    }

    @Override
    public void removeDataEventListener(GroupChangeListener listener) {
        _listeners.remove(listener);
    }

    @Override
    public Set<GroupChangeListener> getListeners() {
        return Collections.unmodifiableSet(_listeners);
    }

    private void notifyListeners() {
        for (GroupChangeListener listener: _listeners) {
          listener.groupChanged();
        }
      }
}
