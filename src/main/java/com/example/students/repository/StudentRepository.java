package com.example.students.repository;

import com.example.students.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    Optional<StudentEntity> findByUsername(String username);
}
