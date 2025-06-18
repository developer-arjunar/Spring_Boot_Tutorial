package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import com.luv2code.demo.entity.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    // Define @PostConstruct to load student data.. only once
    @PostConstruct
    public void loadData() {
        studentList = new ArrayList<>();

        studentList.add(new Student("Arjuna", "Randeniya"));
        studentList.add(new Student("Gihan", "Perera"));
        studentList.add(new Student("Lakshan", "Chalaka"));
        studentList.add(new Student("Sachith", "Madhushan"));
    }

    // Define endpoint for return a list of students("/students")
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentList;
    }

    // Define endpoint for return a student at index("/students/{studentId}")
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if ((studentId >= studentList.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student Details not found : " + studentId);
        }

        return studentList.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
