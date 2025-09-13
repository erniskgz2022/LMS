package service;

import models.Group;

import java.util.List;

public interface GroupService {

    List<Group> addGroup(Group group);
    List<Group> getAllGroup();
    Group getGroupByName(String groupName);
    Group updateGroupName(String groupName, Group newGroup);
    Group deleteGroup(String groupName);
}
