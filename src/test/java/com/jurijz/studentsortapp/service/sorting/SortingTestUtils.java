package com.jurijz.studentsortapp.service.sorting;


import com.jurijz.studentsortapp.domain.Student;

import java.util.List;

/**
 * Created by jurijz on 6/19/2017.
 */
public class SortingTestUtils {

    public static void printList(List<Student> studentList) {
        studentList.forEach(student ->
                System.out.println(student.getName() + " - " +
                        String.format("%.2f", student.getPerformance())));
    }
}
