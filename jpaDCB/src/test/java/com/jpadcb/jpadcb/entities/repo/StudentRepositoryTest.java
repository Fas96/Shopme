package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Guardian;
import com.jpadcb.jpadcb.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    //
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Guardian guardian= Guardian.builder()
                .email("nik@gmail")
                .mobile("010101010101")
                .name("newsnikels")
                .build();

        Student student= Student.builder()
                .emailId("s@mgail.come")
                .firstName("fas")
                .lastName("bhim")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
        System.out.println("-----------------------------------");


        System.out.println("-----------------------------------");

    }

    @Test
    public  void printAllStudents(){
        List<Student> findAll = studentRepository.findAll();

        System.out.println("-----------------------------------");
        System.out.println(findAll);

        System.out.println("-----------------------------------");

    }
    @Test
    public  void printStudentsByFirstName(){
        List<Student> findAll = studentRepository.findByFirstName("fas");

        System.out.println("---------------first name--------------------");
        System.out.println(findAll);

        System.out.println("-----------------------------------");

    }
    @Test
    public  void printStudentfindByFirstNameContaining(){
        List<Student> findAll = studentRepository.findByFirstNameContaining("f");

        System.out.println("---------------first name--------------------");
        System.out.println(findAll);

        System.out.println("-----------------------------------");

    }

    @Test
    public  void printStudentfindByLastNameNotNull(){
        List<Student> findAll = studentRepository.findByLastNameNotNull();

        System.out.println("---------------first name--------------------");
        System.out.println(findAll);

        System.out.println("-----------------------------------");

    }
    @Test
    public  void printStudentfindByGuardianName(){
        List<Student> findAll = studentRepository.findByGuardianName("nikel");

        System.out.println("---------------first name--------------------");
        System.out.println(findAll);

        System.out.println("-----------------------------------");

    }

}