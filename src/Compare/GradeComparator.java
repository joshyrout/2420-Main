package Compare;

import java.util.Comparator;

public class GradeComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2)
    {
        return s2.getGrade() - s1.getGrade();
    }
}

