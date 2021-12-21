package com.example.studentdetailapi.repository;

import com.example.studentdetailapi.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
    Student findByFirstName(String firstName);
}
