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
@Table(name = "tbl_course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence",initialValue = 1,sequenceName = "course_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")
    private Long courseId;
    private String title;
    private int credit;

     @OneToOne(mappedBy = "course",fetch = FetchType.EAGER)
    private CourseMaterial courseMaterial;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
     private Teacher teacher;


     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(
             name = "student_courses",
             joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "courseId"),
             inverseJoinColumns = @JoinColumn(name ="student_id",referencedColumnName = "studentId"))

     private List<Student> student;
     public void addStudent(Student student){
         if(this.student==null)this.student=new ArrayList<>();
         this.student.add(student);
     }
}
