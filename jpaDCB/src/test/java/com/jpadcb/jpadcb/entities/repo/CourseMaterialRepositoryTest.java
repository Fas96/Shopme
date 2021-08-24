package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Course;
import com.jpadcb.jpadcb.entities.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    public  void saveCourseMaterial(){
        Course course= Course.builder()
                .credit(500)
                .title("JAVA")
                .build();
        CourseMaterial courseMaterial= CourseMaterial.builder()
                .url("www.dailyfasbuffer.com")
                //org.springframework.dao.DataIntegrityViolationException:
                // not-null property references a null or transient value
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }


    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials=courseMaterialRepository.findAll();

        System.out.println("--course material----");
        System.out.println(courseMaterials);
        System.out.println("--course material----");

    }

}