package com.jurijz.studentsortapp.service.input;

import com.jurijz.studentsortapp.domain.Student;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jurijz on 10/13/2018.
 */
public interface InputFile {

    List<Student> readFile(File file) throws IOException;

    List<Student> readStream(InputStream inputStream) throws IOException;
}
