package com.example.students.controller;

import com.example.students.dto.StudentDTO;
import com.example.students.entity.StudentEntity;
import com.example.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/all")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/username")
    public ResponseEntity<StudentEntity> getStudentByUsername(@RequestParam String username){
        return studentService.getStudentByUsername(username);
    }

    @PostMapping("/add")
    public StudentEntity addStudent(@RequestBody StudentDTO student){
        return studentService.saveStudentEntity(student);
    }

    @PutMapping("/{id}")
    public StudentEntity updateStudent(@RequestBody StudentDTO student, @PathVariable int id){
        return studentService.updateStudentById(student,id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudentById(id);
    }
}
