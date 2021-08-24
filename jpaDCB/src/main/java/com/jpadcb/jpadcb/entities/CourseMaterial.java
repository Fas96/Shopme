package com.jpadcb.jpadcb.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_course_material")
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(name = "courseMaterial_sequence",initialValue = 1,sequenceName = "courseMaterial_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "courseMaterial_sequence")
    private Long courseMaterialId;
    private String url;

    //with no cascading this is the error that happens because course material is not saved in db
    //org.springframework.dao.InvalidDataAccessApiUsageException:
    // org.hibernate.TransientPropertyValueException: object references an unsaved
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false) // allows all to execute
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private Course course;


}
