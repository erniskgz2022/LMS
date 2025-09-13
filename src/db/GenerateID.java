package db;

public class GenerateID {
    private static  Long groupId = 0L;
    private static  Long studentId = 0L;
    private static Long lessonId = 0L;

    public static Long genGroupId(){
        return ++groupId;
    }
    public static Long genStudentId(){
        return ++studentId;
    }
    public static Long genLessonId(){
        return ++lessonId;
    }
}
