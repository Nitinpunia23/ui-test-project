package com.example.studentdetailapi.controller;

import com.example.studentdetailapi.model.Student;
import com.example.studentdetailapi.repository.StudentRepository;
import com.example.studentdetailapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @RequestMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
    return new ResponseEntity<Student>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @RequestMapping("/findByFirstName")
    public ResponseEntity<Student> getStudentByName(@RequestParam("firstName") String firstName){
    return new ResponseEntity<Student>(studentService.getStudentByName(firstName), HttpStatus.OK);
    }

    @RequestMapping("/findAll")
    public ResponseEntity<Iterable<Student>> getAllStudents(){
    return new ResponseEntity<Iterable<Student>>(studentService.getAllStudents(),HttpStatus.OK);
    }

}
