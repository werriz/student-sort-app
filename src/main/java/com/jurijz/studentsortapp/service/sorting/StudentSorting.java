package com.jurijz.studentsortapp.service.sorting;

import com.jurijz.studentsortapp.domain.Student;

import java.util.List;

/**
 * Created by jurijz on 10/13/2018.
 */
public interface StudentSorting {

    List<Student> sort(List<Student> unsortedList);
}
