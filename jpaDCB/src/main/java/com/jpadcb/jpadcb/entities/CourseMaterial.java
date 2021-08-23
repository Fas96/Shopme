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
@Table(name = "tbl_course_material")
public class CourseMaterial {

    @Id
    @SequenceGenerator(name = "courseMaterial_sequence",initialValue = 1,sequenceName = "courseMaterial_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "courseMaterial_sequence")
    private Long courseMaterialId;
    private String url;

    @OneToOne
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private Course course;


}
