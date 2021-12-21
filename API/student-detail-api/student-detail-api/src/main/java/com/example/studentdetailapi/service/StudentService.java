package com.example.studentdetailapi.service;

import com.example.studentdetailapi.model.Student;
import com.example.studentdetailapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
public Iterable<Student> getAllStudents(){
    Iterable<Student> students =studentRepository.findAll();
    return students;

}
    public Student getStudentByName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }
    public Student addStudent(Student student){
Student savedStudent = studentRepository.save(student);
return savedStudent;
    }
}
