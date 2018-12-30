package com.jurijz.studentsortapp.domain;

/**
 * Created by jurijz on 10/13/2018.
 */
public class Student implements Comparable<Student> {

    private String name;
    private double performance;

    public Student() {

    }

    public Student(String name, double performance) {
        this.name = name;
        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(Student student) {
        return student != null ?
                Double.compare(this.performance, student.getPerformance()) :
                1;
    }

    @Override
    public String toString() {
        return this.name + " - result: " + this.performance;
    }
}