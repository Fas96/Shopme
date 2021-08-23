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
@Table(name = "tbl_course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence",initialValue = 1,sequenceName = "course_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")
    private Long courseId;
    private String title;
    private int credit;

    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;
}
