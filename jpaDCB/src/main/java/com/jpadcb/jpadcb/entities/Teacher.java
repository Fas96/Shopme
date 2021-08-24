package com.jpadcb.jpadcb.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_teacher")
public class Teacher {

    @Id
    @SequenceGenerator(name = "teacher_sequence",initialValue = 1,sequenceName = "teacher_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teacher_sequence")
    private Long teacherId;
    private String firstName;
    private String lastName;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
//    private List<Course> courseList=new ArrayList<>();

}
