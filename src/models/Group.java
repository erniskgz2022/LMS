package models;

import db.GenerateID;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Long id;
    private String groupName;
    private String description;
    private List<Lesson> lessons = new ArrayList<>();
    private  List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String groupName, String description) {
        this.id = GenerateID.genGroupId();
        this.groupName = groupName;
        this.description = description;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "~~~~~~~~Group~~~~~~~~~~~" + "\n" +
                "ID: " + id + "\n" +
                "Group Name: " + groupName + '\n' +
                "Description: " + description + '\n' +
                lessons + "\n" +
                students + "\n";
    }
}
