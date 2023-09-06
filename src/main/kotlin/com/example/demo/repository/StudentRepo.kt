package com.example.demo.repository

import com.example.demo.model.Courses
import com.example.demo.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepo : JpaRepository<Student, Int> {
}