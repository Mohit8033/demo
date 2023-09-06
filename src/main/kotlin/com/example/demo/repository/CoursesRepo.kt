package com.example.demo.repository

import com.example.demo.model.Courses
import org.springframework.data.jpa.repository.JpaRepository

interface CoursesRepo : JpaRepository<Courses, Int> {
}