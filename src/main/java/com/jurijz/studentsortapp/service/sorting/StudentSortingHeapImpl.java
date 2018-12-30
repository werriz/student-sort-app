package com.jurijz.studentsortapp.service.sorting;

import com.jurijz.studentsortapp.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jurijz on 10/13/2018.
 */
@Component
public class StudentSortingHeapImpl implements StudentSorting {

    @Override
    public List<Student> sort(List<Student> unsortedList) {
        Student[] students = unsortedList.toArray(new Student[0]);
        int size = students.length;

        // Build heap (rearrange array)
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(students, size, i);
        }

        // One by one extract an element from heap
        for (int i=size-1; i>=0; i--) {
            // Move current root to end
            Student temp = students[0];
            students[0] = students[i];
            students[i] = temp;

            // call max heapify on the reduced heap
            heapify(students, i, 0);
        }
        return new ArrayList<>(Arrays.asList(students));
    }

    private void heapify(Student[] students, int n, int i) {

        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && students[l].getPerformance() > students[largest].getPerformance()) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < n && students[r].getPerformance() > students[largest].getPerformance()) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            Student swap = students[i];
            students[i] = students[largest];
            students[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(students, n, largest);
        }
    }

    @Override
    public String toString() {
        return "Heap sort";
    }
}
