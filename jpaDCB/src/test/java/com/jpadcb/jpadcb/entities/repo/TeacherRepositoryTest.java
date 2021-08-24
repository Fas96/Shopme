package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Course;
import com.jpadcb.jpadcb.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository  teacherRepository;

    @Test
    public void saveTeacher(){
        Course course= Course.builder()
                .title("DBA")
                .credit(3)
                .build();

        Course courjava= Course.builder()
                .title("JAVA")
                .credit(6)
                .build();
        Teacher teacher= Teacher.builder()
                .firstName("Khan")
                .lastName("fas")
              //  .courseList(List.of(course,courjava))
                .build();
        teacherRepository.save(teacher);
    }
}