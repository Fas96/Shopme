package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {


}