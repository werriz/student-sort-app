package com.jurijz.studentsortapp.service.sorting;

import com.jurijz.studentsortapp.domain.Student;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jurijz on 10/13/2018.
 */
@Component
public class StudentSortingBubbleImpl implements StudentSorting {

    @Override
    public List<Student> sort(List<Student> unsortedList) {
        Student[] studentArr = unsortedList.toArray(new Student[0]);

        int n = studentArr.length;
        Student temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (studentArr[j - 1].getPerformance() > studentArr[j].getPerformance()) {
                    temp = studentArr[j - 1];
                    studentArr[j - 1] = studentArr[j];
                    studentArr[j] = temp;
                }

            }
        }

        return Arrays.asList(studentArr);
    }

    @Override
    public String toString() {
        return "Bubble sort";
    }
}
