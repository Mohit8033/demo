package com.example.demo.model

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name = "courses")
class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id = 0

    @Column(name="name")
    private val name: String? = null
}