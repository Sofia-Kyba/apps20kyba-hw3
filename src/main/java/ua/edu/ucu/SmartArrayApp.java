package ua.edu.ucu;

import java.util.Arrays;
import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;

public class SmartArrayApp {

    public static Integer[]

    filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers) {
                
        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) > 0;
            }
        };

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1) - ((Integer) o2);
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return 2 * ((Integer) t);
            }
        };

        // Input: [-1, 2, 0, 1, -5, 3]
        SmartArray sa = new BaseArray(integers);
//
        sa = new FilterDecorator(sa, pr); // Result: [2, 1, 3];
        sa = new SortDecorator(sa, cmp); // Result: [1, 2, 3]
        sa = new MapDecorator(sa, func); // Result: [2, 4, 6]

        // Alternative
//        sa = new MapDecorator(
//                    new SortDecorator(
//                        new FilterDecorator(sa, pr),
//                    cmp),
//                func);

        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, Integer[].class);
    }

    public static String[]
        findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students) {

        MyPredicate pr = new MyPredicate() {
            private Student st;
            @Override
            public boolean test(Object t) {
                this.st = (Student) t;
                int year = st.getYear();
                double gpa = st.getGPA();
                return (year == 2) && (gpa >= 4);
            }
        };

        MyComparator cmp = new MyComparator() {
            private Student st1;
            private Student st2;

            @Override
            public int compare(Object o1, Object o2) {
                this.st1 = (Student) o1;
                this.st2 = (Student) o2;

                int val = 0;
                for (int i = 0; i < st1.getSurname().length(); i++) {
                    for (int j = 0; j < st2.getSurname().length(); j++) {
                        if (st1.getSurname().charAt(i)
                                < st2.getSurname().charAt(i)){
                            val = -1;
                            return val;
                        } else if (st1.getSurname().charAt(i)
                                > st2.getSurname().charAt(i)){
                            val = 1;
                            return val;
                        } else {
                            continue;
                        }
                    }
                }
                return val;
            }
        };


        MyFunction func = new MyFunction() {
            private Student st1;

            @Override
            public Object apply(Object t) {
                this.st1 = (Student) t;
                return st1.getSurname() + " " + st1.getName();
            }
        };

        SmartArray studentSmartArray = new BaseArray(students);

        studentSmartArray = new FilterDecorator(studentSmartArray, pr);
        studentSmartArray = new SortDecorator(studentSmartArray, cmp);
        studentSmartArray = new MapDecorator(studentSmartArray, func);
        studentSmartArray = new DistinctDecorator(studentSmartArray);

        Object[] result = studentSmartArray.toArray();
        return Arrays.copyOf(result, result.length, String[].class);
    }
}
