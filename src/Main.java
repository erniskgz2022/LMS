import Service.GroupService;
import Service.Impl.GroupServiceImpl;
import Service.Impl.LessonServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.LessonService;
import Service.StudentService;
import enums.Gender;
import models.Group;
import models.Lesson;
import models.Student;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GroupService groupService = new GroupServiceImpl();
        StudentService studentService = new StudentServiceImpl();
        LessonService lessonService = new LessonServiceImpl();

        String email = "admin@gmail.com";
        String password = "admin123";

        while (true){
            LocalTime now = LocalTime.now();
            int hour = now.getHour();
            String greeting;

            if (hour > 5 && hour < 12){
                greeting = ("Кутман таң!");
            } else if ( hour >= 12 && hour < 18) {
                greeting = ("Кутман түш!");
            } else if (hour >= 18 && hour < 23) {
                greeting = ("Кутман кеч!");
            }else {
                greeting = ("Кутман түн!");
            }

            System.out.println(greeting + " -> " + now.getHour() + ":" + now.getMinute());

            System.out.print("""
                    Катталган болсоңуз 1 басыныз, пароль унутуп калып, өзгөртүү үчүн 2 басыныз, Программаны токтотуу үчүн 0 басыңыз!
                    """);
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1 -> {
                    System.out.println("Кирүү үчүн электрондук почтаңызды жана паролуңузду жазыңыз!");
                    System.out.print("Электрондук почтаңызды жазыңыз: ");
                    String login = sc.nextLine();
                    System.out.print("Пароль жазыңыз: ");
                    String pass = sc.nextLine();

                    if (login.equals(email) && pass.equals(password)){
                        System.out.println("Кош келиңиз!");
                        boolean inAdminMenu = true;
                        while (inAdminMenu) {
                            System.out.println("""
                                    *** Бир команда тандаңыз! ***
                                    1 - Add new group
                                    2 - Get group by name
                                    3 - Update group name
                                    4 - Get all groups
                                    5 - Delete group
                                    6 - Add new student to group
                                    7 - Update student
                                    8 - Find student by first name
                                    9 - Get all students by group name
                                    10 - Get all student’s lesson
                                    11 - Delete student
                                    12 - Add new lesson to group
                                    13 - Get lesson by name
                                    14 - Get all lessons by group name
                                    15 - Delete lesson
                                    0 - Logout
                                    """);

                            int adminChoice = sc.nextInt();
                            sc.nextLine();

                            switch (adminChoice){
                                case 1 -> {
                                    System.out.print("Жаңы группага ат жазыңыз: ");
                                    String grName = sc.nextLine();
                                    System.out.print("Группанын сүрөттөмөсүн жазыңыз: ");
                                    String ds = sc.nextLine();
                                    Group group = new Group(grName,ds);
                                    groupService.addGroup(group);
                                    System.out.println(grName + " атту группа ийгиликтүү сакталды!");
                                }
                                case 2 -> {
                                    System.out.print("Группанын атын жазыңыз: ");
                                    String grpName = sc.nextLine();
                                    Group g = groupService.getGroupByName(grpName);
                                    if (g != null) {
                                        System.out.println("Табылды: \n" + g);
                                    } else {
                                        System.out.println("Мындай группа табылган жок!");
                                    }

                                }
                                case 3 -> {
                                    System.out.print("Группанын атын жазыңыз: ");
                                    String gName = sc.nextLine();
                                    System.out.print("Жаңы ат жазыныз: ");
                                    String nName = sc.nextLine();
                                    Group oldGroup = groupService.getGroupByName(gName);
                                    if (oldGroup != null) {
                                        Group newGroup = new Group(nName, oldGroup.getDescription());
                                        groupService.updateGroupName(gName, newGroup);
                                        System.out.println(nName + " группа ийгиликтүү өзгөрдүү!");
                                    } else {
                                        System.out.println("Мындай группа табылган жок!!");
                                    }

                                }
                                case 4 -> {
                                    System.out.println(groupService.getAllGroup());

                                }
                                case 5 -> {
                                    System.out.print("Группанын атын жазыңыз: ");
                                    String grName = sc.nextLine();
                                    Group deleted = groupService.deleteGroup(grName);
                                    if (deleted != null) {
                                        System.out.println("Ийгиликтүү өчүрүлдү: " + deleted.getGroupName());
                                    } else {
                                        System.out.println("Мындай группа табылган жок!");
                                    }
                                }
                                case 6 -> {
                                    System.out.print("Кайсы группага кошосуз, ошол группанын атын жазыңыз: ");
                                    String grpName = sc.nextLine();
                                    System.out.print("Студенттин атын жазыңыз: ");
                                    String sName = sc.nextLine();
                                    System.out.print("Фамилиясын жазыныз: ");
                                    String fName = sc.nextLine();
                                    System.out.print("Электрондук почтасын жазыңыз: ");
                                    String eml = sc.nextLine();
                                    System.out.print("Пароль ойлоп табыныз(узундугу 7 символдон аз болбосун!): ");
                                    String pas = sc.nextLine();
                                    System.out.print("Жынысын жазыңыз(MALE/FEMALE): ");
                                    Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
                                    Student student = new Student(sName,fName,eml,pas,gender);
                                    Student added = studentService.addStudentByToGroup(grpName, student);
                                    if (added != null) {
                                        System.out.println("Жаңы студент ийгиликтүү кошулду!");
                                    } else {
                                        System.out.println("Группа табылган жок, студент кошулбады!");
                                    }
                                }
                                case 7 -> {
                                    System.out.print("Студентин ID жазыныз: ");
                                    Long sId = sc.nextLong();
                                    sc.nextLine();
                                    System.out.print("Жаңы атын жазыңыз: ");
                                    String nName = sc.nextLine();
                                    System.out.print("Жаңы фамилиясын жазыңыз: ");
                                    String nLName = sc.nextLine();
                                    System.out.print("Жаңы электрондук почтасын жазыңыз: ");
                                    String nEmail = sc.nextLine();
                                    System.out.print("Жаңы паролун жазыңыз: ");
                                    String nPass = sc.nextLine();
                                    System.out.print("Жынысын жазыңыз(MALE/FEMALE): ");
                                    Gender nGender = Gender.valueOf(sc.nextLine().toUpperCase());
                                    Student newStudent = new Student(nName,nLName,nEmail,nPass,nGender);
                                    studentService.updateStudentById(sId,newStudent);
                                    System.out.println("Сиздин маалыматыныз ийгиликтүү өзгөрдү!!");
                                }
                                case 8 -> {
                                    System.out.print("Студентин атын жазыңыз: ");
                                    String fName = sc.nextLine();
                                    System.out.println(studentService.findStudentByFirstName(fName));
                                }
                                case 9 -> {
                                    System.out.print("Группанын атын жазыңыз: ");
                                    String gName = sc.nextLine();
                                    System.out.println(studentService.getAllStudentsByGroupName(gName));
                                }
                                case 10 -> {
                                    System.out.print("Студенттин почтасын жазыңыз: ");
                                    String sEmail = sc.nextLine();
                                    System.out.println(studentService.getAllStudentsLesson(sEmail));
                                }
                                case 11 -> {
                                    System.out.print("Кимди өчүрөсүз ошол студентин почтасын жазыңыз: ");
                                    String sEmail2 = sc.nextLine();
                                    Student deleted = studentService.deleteStudentByEmail(sEmail2);
                                    if (deleted != null){
                                        System.out.println("Почтасы " + sEmail2 + " болгон студент ийгиликтүү өчүрүлдү!");
                                    }else {
                                        System.out.println("Мындай почта дагы студент табылган жок!");
                                    }

                                }
                                case 12 -> {
                                    System.out.print("Гпуппанын атын жазыңыз: ");
                                    String gpName = sc.nextLine();
                                    System.out.print("Сабактын атын жазыңыз: ");
                                    String lName = sc.nextLine();
                                    System.out.print("Тапшырманын сүрөттөмөсүн жазыңыз: ");
                                    String dsc = sc.nextLine();
                                    Lesson lesson = new Lesson(lName,dsc);
                                    lessonService.addLessonToGroup(gpName,lesson);
                                    System.out.println("Сабак ийгиликтүү сакталды!");
                                }
                                case 13 -> {
                                    System.out.print("Сабактын атын жазыңыз: ");
                                    String lName = sc.nextLine();
                                    System.out.println(lessonService.getLessonByName(lName));
                                }
                                case 14 -> {
                                    System.out.print("Группанын атын жазыңыз: ");
                                    String gName = sc.nextLine();
                                    System.out.println(lessonService.getAllLessonByGroupName(gName));
                                }
                                case 15 -> {
                                    System.out.print("Сабактын атын жазыңыз: ");
                                    String lName = sc.nextLine();
                                    Lesson deleted = lessonService.deleteLessonByName(lName);
                                    if (deleted != null) {
                                        System.out.println("Сабак ийгиликтүү өчтү!");
                                    }else {
                                        System.out.println("Мындай сабак табылган жок!");
                                    }
                                }
                                case 0 -> {
                                    System.out.println("Баштапкы менюга кайтып жатасыз...");
                                    inAdminMenu = false;
                                }

                            }
                        }
                    }else {
                        System.out.println("Почта же пароль туура эмес!");
                    }

                }
                case 2 -> {
                    System.out.print("Электрондук почтаңызды жазыңыз: ");
                    String login = sc.nextLine();
                    System.out.print("Жаңы паролуңузду жазыңыз: ");
                    String newPass = sc.nextLine();
                    if (login.equals(email)){
                        password = newPass;
                        System.out.println("Админ паролу ийгиликтүү өзгөрдү!");
                    }else {
                        System.out.println("Мындай логин табылган жок!");
                    }
                }
                case 0 -> {
                    System.out.println("Программа токтотулду!");
                    return;
                }
                default -> System.out.println("Туура эмес тандоо!");
            }

        }

    }
}