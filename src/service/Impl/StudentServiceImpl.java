package service.Impl;

import service.StudentService;
import db.Database;
import models.Group;
import models.Lesson;
import models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public Student addStudentByToGroup(String groupName, Student student) {
        try {
            for (Group group : Database.groups) {
                for (Student s : group.getStudents()) {
                    if (s.getEmail().equals(student.getEmail())) {
                        System.out.println("Бул email мурунтан колдонулган!");
                        return null;
                    }
                }
            }
            if (student.getPassword().length() < 7) {
                System.out.println("Парол 7 символдон узун болушу керек!");
                return null;
            }
            for (Group group : Database.groups) {
                if (group.getGroupName().equals(groupName)){
                    group.getStudents().add(student);
                    return student;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Student updateStudentById(Long id, Student newStudent) {
        try {
            for (Group group : Database.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getId().equals(id)){
                        student.setFirsName(newStudent.getFirstName());
                        student.setLastName(newStudent.getLastName());
                        student.setEmail(newStudent.getEmail());
                        student.setPassword(newStudent.getPassword());
                        return student;
                    }
                }
            }
            System.out.println("Бул id менен табылган жок!");
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
        return null;
    }

    @Override
    public Student findStudentByFirstName(String firstName) {
        try {
            for (Group group : Database.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getFirstName().equals(firstName)){
                        return student;
                    }
                }
            }
            System.out.println(firstName + " атту студент табылган жок!!!");
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Student> getAllStudentsByGroupName(String groupName) {
        try {
            for (Group group : Database.groups) {
                if (group.getGroupName().equals(groupName)){
                    return group.getStudents();
                }

            }
            System.out.println("Мындай группа ат табылган жок!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Lesson> getAllStudentsLesson(String email) {
        List<Lesson> result = new ArrayList<>();
        try {
            for (Group group : Database.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getEmail().equals(email)) {
                        if (group.getLessons() != null) {
                            result.addAll(group.getLessons());
                        }
                        return result;
                    }
                }
            }
            System.out.println("Мындай почта менен студент табылган жок!");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Student deleteStudentByEmail(String email) {
        try {
            if (email == null){
                throw new RuntimeException("Email null боло албайт!");
            }
            for (Group group : Database.groups) {
                for (Student student : group.getStudents()) {
                    if (student.getEmail().equals(email)){
                        group.getStudents().remove(student);
                        return student;
                    }
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
