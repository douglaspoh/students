package com.example.students.service;

import com.example.students.model.UserDetailsModel;
import com.example.students.dto.StudentDTO;
import com.example.students.entity.StudentEntity;
import com.example.students.exception.StudentNotFoundException;
import com.example.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.students.constant.ApplicationConstants.NO_STUDENT_FOUND_ERROR_DETAILS;


@Service
public class StudentService implements UserDetailsService {
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
        studentEntity.setUsername(student.getName());
        studentEntity.setPassword("password");
        studentEntity.setRole("ROLE_ADMIN");
        studentEntity.setAge(student.getAge());
    }

    public ResponseEntity<StudentEntity> getStudentById(int id){
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException(NO_STUDENT_FOUND_ERROR_DETAILS));
        return ResponseEntity.ok().body(studentEntity);
    }

    public ResponseEntity<StudentEntity> getStudentByUsername(String username){
        StudentEntity studentEntity = studentRepository.findByUsername(username)
                .orElseThrow(()-> new StudentNotFoundException(NO_STUDENT_FOUND_ERROR_DETAILS));
        return ResponseEntity.ok().body(studentEntity);

    }

    public StudentEntity updateStudentById(StudentDTO student, int id){
        StudentEntity studentEntity = studentRepository.findById(id)
                        .orElseThrow(()-> new StudentNotFoundException(NO_STUDENT_FOUND_ERROR_DETAILS));

        studentEntity.setUsername(student.getName());
        studentEntity.setAge(student.getAge());
        final StudentEntity updatedStudentEntity = studentRepository.save(studentEntity);
        return updatedStudentEntity;
    }

    public void deleteStudentById(int id){
        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException(NO_STUDENT_FOUND_ERROR_DETAILS));
        studentRepository.delete(studentEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        StudentEntity student = studentRepository.findByUsername(username)
                .orElseThrow(()-> new StudentNotFoundException("Student with username \'" + username + "\' not found"));

        return new UserDetailsModel(student);
    }
}
