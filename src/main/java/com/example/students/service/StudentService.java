package com.example.students.service;

import com.example.students.dto.StudentDTO;
import com.example.students.entity.StudentEntity;
import com.example.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    StudentEntity studentEntity = new StudentEntity();

    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }

    public StudentEntity saveStudentEntity(StudentDTO student){
        populateStudentEntity(student);
        return studentRepository.save(studentEntity);
    }

    public void populateStudentEntity(StudentDTO student){
        studentEntity.setId(student.getId());
        studentEntity.setName(student.getName());
        studentEntity.setAge(student.getAge());
    }

    public ResponseEntity<StudentEntity> getStudentById(int id){
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());
        return ResponseEntity.ok().body(studentEntity);
    }

    public StudentEntity updateStudentById(StudentDTO student, int id){
        StudentEntity studentEntity = studentRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException());
        studentEntity.setName(student.getName());
        studentEntity.setAge(student.getAge());
        final StudentEntity updatedStudentEntity = studentRepository.save(studentEntity);
        return updatedStudentEntity;
    }

    public void deleteStudentById(int id){
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());
        studentRepository.delete(studentEntity);
    }
}
