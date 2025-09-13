package service;

import models.Lesson;

import java.util.List;

public interface LessonService {
    Lesson addLessonToGroup(String groupName,Lesson lesson);
    Lesson getLessonByName(String lessonName);
    List<Lesson> getAllLessonByGroupName(String groupName);
    Lesson deleteLessonByName(String lessonName);
}
