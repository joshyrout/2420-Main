package Compare;

import edu.princeton.cs.algs4.Selection;
import java.util.Arrays;

public class MainComparatorExample {

    public static void main(String args[]) {
        Student student[] = new Student[8];

        student[0] = new Student("Rick",65,"Quantum Physics", 19);
        student[1] = new Student("Summer", 16, "Social media", 12);
        student[2] = new Student("Morty", 14, "Math", 16);
        student[3] = new Student("Jerry", 14, "Math", 6);
        student[4] = new Student("Beth", 14, "Astrphysics", 21);
        student[5] = new Student("Mabel", 19, "Knitting", 14);
        student[6] = new Student("Dipper", 19, "Biblitography", 18);
        student[7] = new Student("Wendy", 21, "Logging", 11);
        System.out.println("Order of students before sorting is: ");
        for (int i = 0; i < student.length; i++) {
            System.out.println(student[i].toString());
        }
        Selection.sort(student, new GradeComparator());
        System.out.println("Order of students after sorting by student grade is");
        for (int i = 0; i < student.length; i++) {
            System.out.println(student[i].toString());
        }
        Arrays.sort(student, new NameComparator());
        System.out.println("Order of students after sorting by student name is");
        for (int i = 0; i < student.length; i++) {
            System.out.println(student[i].toString());
        }
    }
}
