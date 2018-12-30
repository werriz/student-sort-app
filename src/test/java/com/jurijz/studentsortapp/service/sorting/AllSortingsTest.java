package com.jurijz.studentsortapp.service.sorting;

import com.jurijz.studentsortapp.domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by jurijz on 11/9/2018.
 */
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class AllSortsTest {

    private StudentSorting bubbleSort;
    private StudentSorting mergeSort;
    private StudentSorting heapSort;

    private List<Student> unsortedList;


    @BeforeAll
    void init() {
        unsortedList = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            unsortedList.add(new Student("student" + i,
                    ThreadLocalRandom.current().nextDouble(0, 10)));
        }
        bubbleSort = new StudentSortingBubbleImpl();
        mergeSort = new StudentSortingMergeImpl();
        heapSort = new StudentSortingHeapImpl();
    }

    @Test
    void allSortTest_success()  {
        System.out.println("unsorted list");
        SortingTestUtils.printList(unsortedList);

        System.out.println("BubbleSort");
        List<Student> studentList;
        studentList = bubbleSort.sort(new ArrayList<>(unsortedList));

        assertNotNull(studentList, "Sorted list is null");
        assertTrue((studentList.get(studentList.size() - 1)
                        .compareTo(studentList.get(studentList.size() - 2))) > 0,
                "Last element is less than element before");
        System.out.println("sortedList");
        SortingTestUtils.printList(studentList);

        System.out.println("MergeSort");
        studentList = mergeSort.sort(new ArrayList<>(unsortedList));

        assertNotNull(studentList, "Sorted list is null");
        assertTrue((studentList.get(studentList.size() - 1)
                        .compareTo(studentList.get(studentList.size() - 2))) > 0,
                "Last element is less than element before");
        System.out.println("sortedList");
        SortingTestUtils.printList(studentList);

        System.out.println("HeapSort");
        studentList = heapSort.sort(new ArrayList<>(unsortedList));

        assertNotNull(studentList, "Sorted list is null");
        assertTrue((studentList.get(studentList.size() - 1)
                        .compareTo(studentList.get(studentList.size() - 2))) > 0,
                "Last element is less than element before");
        System.out.println("sortedList");
        SortingTestUtils.printList(studentList);

    }
}
