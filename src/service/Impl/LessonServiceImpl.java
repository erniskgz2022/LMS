package service.Impl;

import service.LessonService;
import db.Database;
import models.Group;
import models.Lesson;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    @Override
    public Lesson addLessonToGroup(String groupName, Lesson lesson) {
        try {
            for (Group group : Database.groups) {
                if (group.getGroupName().equals(groupName)){
                    group.getLessons().add(lesson);
                    return lesson;
                }
            }
            System.out.println("Мындай группа табылган жок!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Lesson getLessonByName(String lessonName) {
        try {
            for (Group group : Database.groups) {
                for (Lesson lesson : group.getLessons()) {
                    if (lesson.getLessonName().equals(lessonName)){
                        return lesson;
                    }
                }
            }
            System.out.println("Мындай ат менен сабак табылган жок!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllLessonByGroupName(String groupName) {
        try {
            for (Group group : Database.groups) {
                if (group.getGroupName().equals(groupName)){
                    return group.getLessons();
                }
            }
            System.out.println("Мындай группа табылган жок!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson deleteLessonByName(String lessonName) {
        try {
            if (lessonName == null){
                throw new RuntimeException("LessonName null боло албайт!");
            }
            for (Group group : Database.groups) {
                for (Lesson lesson : group.getLessons()) {
                    if (lesson.getLessonName().equals(lessonName)){
                        group.getLessons().remove(lesson);
                        return lesson;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
