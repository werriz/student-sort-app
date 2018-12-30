package com.jurijz.studentsortapp.service.sorting;

import com.jurijz.studentsortapp.domain.Student;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jurijz on 10/13/2018.
 */
@Component
public class StudentSortingMergeImpl implements StudentSorting {


    private Student[] array;
    private Student[] helper;

    @Override
    public List<Student> sort(List<Student> list) {
        array = list.toArray(new Student[0]);
        helper = new Student[array.length];
        mergeSort(0, array.length - 1);
        list = Arrays.asList(array);
        return list;
    }

    private void mergeSort(int low, int high) {
        if(low < high) {
            int middle = low + (high - low) / 2;
            mergeSort(low, middle); //sort left half
            mergeSort(middle + 1, high); //sort right half
            merge(low, middle, high); // merge
        }
    }

    private void merge(int low, int middle, int high) {
        //This loop throws Exception
        if (high + 1 - low >= 0) {
            System.arraycopy(array, low, helper, low,
                    high + 1 - low);
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        //Iterate through helper array, copying back smaller element in the original list
        while((helperLeft <= middle) && (helperRight <= high)) {
            if(helper[helperLeft].compareTo(helper[helperRight]) < 1) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        while (helperLeft <= middle) {
            array[current] = helper[helperLeft];
            current++;
            helperLeft++;
        }

    }


    @Override
    public String toString() {
        return "Merge sort";
    }
}
