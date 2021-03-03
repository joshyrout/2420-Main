package Compare;
import java.util.Comparator;

public class Student implements Comparable<Student> {

    public static final Comparator<Student> BY_LESSON = new LessonComparator();
    private String name;
    private int age;
    private String lesson;
    private int grade;

    public Student(String name, int age, String lesson, int grade) {
        this.name = name;
        this.age = age;
        this.lesson = lesson;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "[name=" + this.name + ", age=" + this.age + ", lesson="
                + this.lesson + ", grade=" + this.grade + "]";
    }

    @Override
    public int compareTo(Student o) {
        return this.getAge() - o.getAge();
    }

    private static class LessonComparator implements Comparator<Student>{

        @Override
        public int compare(Student s1, Student s2) {

            String lesson1 = s1.getLesson();
            String lesson2 = s2.getLesson();
            // Returns the results of the compared strings
            return lesson1.compareTo(lesson2);
        }
    }

}
