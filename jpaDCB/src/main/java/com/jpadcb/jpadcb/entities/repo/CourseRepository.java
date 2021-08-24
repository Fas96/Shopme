package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Course;
import com.jpadcb.jpadcb.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Page<Course> findByTitleContaining(String title, Pageable preq);


}