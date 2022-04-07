package com.example.students.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table
@Data
public class StudentEntity {

    @Id
    private int id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private int age;
}
