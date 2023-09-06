package com.example.demo.repository

import com.example.demo.model.Courses
import com.example.demo.model.Marks
import org.springframework.data.jpa.repository.JpaRepository

interface MarksRepo : JpaRepository<Marks, Int> {
}