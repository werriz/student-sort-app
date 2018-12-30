package com.jurijz.studentsortapp.service.input;

import com.jurijz.studentsortapp.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by jurijz on 6/17/2017.
 */
class InputTxtFileTest {

    private InputFile inputFile;

    @BeforeEach
    void setUp() {
        inputFile = new InputTxtFile();
    }


    @Test
    void testInputTxtFile_success() throws IOException{
        List<Student> resultList;
        resultList = inputFile.readFile(readFile("testStudentFile"));

        assertNotNull(resultList, "InputFile has no results: ");
        assertTrue(resultList.size() > 0, "Result list is empty ");
    }

    private File readFile(String fileName) throws IOException {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file;

        if (url != null) {
            file = new File(url.getFile());
        } else {
            throw new FileNotFoundException("There is no such file in resources folder: " + fileName);
        }

        return file;
    }
}
