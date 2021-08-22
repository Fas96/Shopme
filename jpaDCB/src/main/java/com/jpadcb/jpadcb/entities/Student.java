package com.jpadcb.jpadcb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_student",uniqueConstraints = @UniqueConstraint(
        name = "emailid_unqiue",
        columnNames = "email_address"
))
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",initialValue = 1,sequenceName = "student_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_sequence")
    private Long studentId;

    private String firstName;
    private String lastName;
    @Column(name = "email_address",nullable = false)
    private String emailId;

    @Embedded
    private Guardian guardian;


}
