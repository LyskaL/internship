package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.GroupUsers;
import entities.User;
import entities.User.Gender;
import entities.User.GroupType;
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

        List<User> users = new ArrayList<>();
        users.add(new User("aliz", "gmail.com", "Alize", Gender.FEMALE, GroupType.FRIENDS));
        users.add(new User("_ssid", "mail.ru", "Sid", Gender.MALE, GroupType.FRIENDS));
        users.add(new User("max", "yandex.ru", "Maxim", Gender.MALE, GroupType.FRIENDS));
        users.add(new User("sye", "gmail.com", "Sye", Gender.FEMALE, GroupType.FAMILY));
        users.add(new User("kost", "mail.ru", "Konstantin", Gender.FEMALE, GroupType.FAMILY));
        users.add(new User("den", "mail.ru", "Denis", Gender.MALE, GroupType.OTHER));
        users.add(new User("kost", "mail.ru", "Konstantin", Gender.FEMALE, GroupType.BUSINESS));

        _group = new GroupUsers(users, "Contacts", null);
    }

    public static GroupServiceImpl getInstance() {
        return _servise;
    }

    @Override
    public void addUser(User user) {
        _group.addUser(user);
        notifyListeners();
    }

    @Override
    public void removeUser(User user) {
        _group.removeUser(user);
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
