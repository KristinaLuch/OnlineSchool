import service.LectureService;

public class Run {
    public static void main(String[] args) {
        LectureService lectureService = new LectureService();
        lectureService.addLecture(1,"1", "1", "1");
        lectureService.addLecture(1,"1", "1", "1");
        lectureService.addLecture(1,"1", "1", "1");
        System.out.println(lectureService.countLecture);
    }
}
