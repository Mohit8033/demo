package com.example.demo.model

import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name = "marks")
class Marks {
    @Id
    private val courseId: Int? = null

    @Column(name="course_name")
    private val courseName: String? = null

    @Column(name="marks_obtained")
    private val marksObtained: Int? = 0

    @Column(name="total_marks")
    private val totalMarks: String? = null

    @Column(name="student_id")
    private val studentId: Int? = null
}