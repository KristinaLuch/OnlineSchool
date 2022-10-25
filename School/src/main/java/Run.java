import entity.Course;
import entity.Lecture;
import service.CourseService;
import service.LectureService;

public class Run {
    public static void main(String[] args) {
        CourseService courseService = new CourseService();
        LectureService lectureService = new LectureService();
        Course course = courseService.createCourse();
        courseService.addLecture(course, lectureService.createLecture("1", "1", "1"));
        courseService.addLecture(course, lectureService.createLecture("2", "1", "1"));
        courseService.addLecture(course, lectureService.createLecture("3", "1", "1"));
        courseService.addLecture(course, lectureService.createLecture("4", "1", "1"));
        courseService.addLecture(course, lectureService.createLecture("5", "1", "1"));
        courseService.addLecture(course, lectureService.createLecture("6", "1", "1"));

        System.out.println(course.getLectures().toString());
        System.out.println("Кількість створених лекцій - "+ Lecture.count);

        System.out.println("HEEEEEEEEEELP");
        System.out.println("i dont understand");

    }
}
