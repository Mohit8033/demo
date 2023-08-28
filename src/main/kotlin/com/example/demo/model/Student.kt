package com.example.demo.model
import lombok.Data
import javax.persistence.*


@Data
@Entity
@Table(name = "student")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id = 0

    @Column(name="name")
    private val name: String? = null

    @Column(name="roll_no")
    private val rollNo: Int? = 0

    @Column(name="email")
    private val email: String? = null

    @Column(name="mobile")
    private val mobile: String? = null

    @Column(name="address")
    private val address: String? = null
}