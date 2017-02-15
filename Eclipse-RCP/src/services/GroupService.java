package services;

import entities.GroupUsers;
import entities.User;
import events.GroupChangeGenerator;

public interface GroupService extends GroupChangeGenerator {

    void addUser(User user, GroupUsers toSubGroup);
    void addSubGroup(GroupUsers user);

    void removeUser(User user);
    void removeSubGroup(GroupUsers user);

    GroupUsers getGroupRoot();
}
