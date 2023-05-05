package hello.student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private List<Course> courses = new ArrayList<>();
    private List<Integer> reportCard = new ArrayList<>();
    private List<String> attendanceBook = new ArrayList<>();

    // 학생 클래스가 잘 할 수 있는것
    public List<Course> getCourses() {
        return courses;
    }

    // 학생 클래스가 잘 할 수 있는것
    public void addCourse(Course c) {
        courses.add(c);
    }

    // 다른 클래스가 잘 할 수 있는 것
    public void printOnReportCard() {
        for (int report : reportCard) {
            System.out.println(report);
        }
    }

    // 다른 클래스가 잘 할 수 있는 것
    public void printOnAttendanceBook() {
        for (String attendance : attendanceBook) {
            System.out.println(attendance);
        }
    }
}
