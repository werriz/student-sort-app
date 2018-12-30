package com.jurijz.studentsortapp.service.input;

import com.jurijz.studentsortapp.domain.Student;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jurijz on 10/13/2018.
 */
@Service
public class InputTxtFile implements InputFile {

    private static final String SPLIT_BY = ",";

    @Override
    public List<Student> readFile(File file) throws IOException {
        return readStream(new FileInputStream(file));
    }

    @Override
    public List<Student> readStream(InputStream inputStream) throws IOException {
        List<Student> studentList = new ArrayList<>();

        int lineNumber = 1;
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] resultsArr = line.split(SPLIT_BY);
                studentList.add(transform(resultsArr));
                lineNumber++;
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IOException(
                    "Failed to read file at line: " + lineNumber + " - " +
                    e.getMessage());
        }

        return studentList;
    }

    /**
     * transforms line to a Student object
     * @param studentArray String[]
     * @return student
     * @throws IOException on bad parsed data
     */
    private Student transform(String[] studentArray) throws IOException{
        if (studentArray.length < 2) {
            throw new IOException("Not enough data to input student");
        }
        Student student = new Student();
        student.setName(studentArray[0]);
        student.setPerformance(Double.parseDouble(studentArray[1]));

        return student;
    }
}
