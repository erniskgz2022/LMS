package Service;

import models.Lesson;
import models.Student;

import java.util.List;

public interface StudentService {

    Student addStudentByToGroup(String groupName, Student student);
    Student updateStudentById(Long id,Student newStudent);
    Student findStudentByFirstName(String firstName);
    List<Student> getAllStudentsByGroupName(String groupName);
    List<Lesson> getAllStudentsLesson(String email);
    Student deleteStudentByEmail(String email);
}
