package services;

import entities.GroupUsers;
import entities.User;
import events.GroupChangeGenerator;

public interface GroupService extends GroupChangeGenerator {

    void addUser(User user);
    void removeUser(User user);

    GroupUsers getGroupRoot();
}
