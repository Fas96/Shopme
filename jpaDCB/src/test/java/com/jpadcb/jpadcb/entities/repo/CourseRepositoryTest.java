package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Course;
import com.jpadcb.jpadcb.entities.Student;
import com.jpadcb.jpadcb.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void listAllCourse(){
        List<Course> courses =courseRepository.findAll();

        System.out.println("------------courses--------");
        System.out.println(courses);
        System.out.println("------------courses--------");
    }

    @Test
    public  void saveCourseWithTeacher(){
        Teacher teacher= Teacher.builder()
                .lastName("fas")
                .firstName("bhimnantions")
                .build();
        Course course= Course.builder()
                .title("Python")
                .credit(49)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public  void findPageNation(){
        PageRequest firstpagewith3Records=  PageRequest.of(0,3);
        PageRequest firstpagewith2Records=  PageRequest.of(1,2);

        List<Course> courses=courseRepository.findAll(firstpagewith3Records).getContent();
        List<Course> coursesR=courseRepository.findAll(firstpagewith2Records).getContent();

        System.out.println("================================");
        System.out.println(courses);
        System.out.println("================================");

        long totalElements=courseRepository.findAll(firstpagewith3Records).getTotalElements();
        long totalPages=courseRepository.findAll(firstpagewith3Records).getTotalPages();

        Sort getSort=courseRepository.findAll(firstpagewith3Records).getSort();
        System.out.println("totalELements: "+totalElements+"  totalPages: "+totalPages);


        System.out.println("==============Second==================");
        System.out.println(coursesR);
        System.out.println("================================");
    }


    @Test
    public  void findPageNationAndSort(){
        PageRequest firstpagewith3Records=  PageRequest.of(0,2,Sort.by("title"));
        PageRequest firstpagewith2Records=  PageRequest.of(1,2,Sort.by("credit").descending());
        PageRequest sortBytitleAndCreditDesc=  PageRequest.of(0,3,Sort.by("credit").descending().and(Sort.by("credit")));

        List<Course> courses=courseRepository.findAll(firstpagewith3Records).getContent();
        List<Course> coursesR=courseRepository.findAll(firstpagewith2Records).getContent();
        List<Course> sBTACD=courseRepository.findAll(sortBytitleAndCreditDesc).getContent();

        System.out.println("==========sBTACD=====================");
        System.out.println(sBTACD);
        System.out.println("================================");


        System.out.println("==========asc======================");
        System.out.println(courses);
        System.out.println("================================");

        long totalElements=courseRepository.findAll(firstpagewith3Records).getTotalElements();
        long totalPages=courseRepository.findAll(firstpagewith3Records).getTotalPages();

        Sort getSort=courseRepository.findAll(firstpagewith3Records).getSort();
        System.out.println("totalELements: "+totalElements+"  totalPages: "+totalPages);


        System.out.println("==============Second=====dec=============");
        System.out.println(coursesR);
        System.out.println("================================");
    }

    @Test
    public void printFindByTitleContaining(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Course> courseList = courseRepository.findByTitleContaining("J", pageRequest).getContent();

        System.out.println(courseList);
    }


    @Test
    public  void saveCourseWithTeacherAndStudent(){
        Teacher teacher= Teacher.builder()
                .lastName("fas")
                .firstName("bhimnantions")
                .build();

        Student student=Student.builder()
                .firstName("fas")
                .firstName("anyas")
                .emailId("firibuanys@gmail.com")
                .build();
        Course course= Course.builder()
                .title("Python")
                .credit(49)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }

}