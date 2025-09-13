package Service.Impl;

import Service.GroupService;
import db.Database;
import models.Group;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    @Override
    public List<Group> addGroup(Group group) {
        try {
            Database.groups.add(group);
            return Database.groups;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

       return null;
    }

    @Override
    public List<Group> getAllGroup() {
        return Database.groups;
    }

    @Override
    public Group getGroupByName(String groupName) {
        try {
            for (Group group : Database.groups) {
                if (group.getGroupName().equals(groupName)){
                    return group;
                }
            }
        }catch (Exception e ){
            System.out.println("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public Group updateGroupName(String groupName, Group newGroup) {
        try {
            Group oldGroup = getGroupByName(groupName);
            if (oldGroup == null){
                throw new RuntimeException("Мындай группа аты менен табылган жок!");
            }

            for (Group g : Database.groups) {
                if (g != oldGroup && g.getGroupName().equals(newGroup.getGroupName())) {
                    throw new RuntimeException("Группанын аты окшош боло албайт!");
                }
            }

            oldGroup.setGroupName(newGroup.getGroupName());
            System.out.println("Группа ийгиликтүү өзгөрдү!");
            return oldGroup;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Group deleteGroup(String groupName) {
        try {
            if (groupName == null){
                throw new RuntimeException("Группа аты null боло албайт!");
            }
            Group deleteGroup = getGroupByName(groupName);
            if (deleteGroup != null){
                Database.groups.remove(deleteGroup);
                return deleteGroup;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
