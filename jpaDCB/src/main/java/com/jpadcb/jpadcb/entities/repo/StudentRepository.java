package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public List<Student> findByFirstName(String firstName);
    public  List<Student> findByFirstNameContaining(String name);
    public  List<Student> findByLastNameNotNull();
    public  List<Student>  findByGuardianName(String gname);
}