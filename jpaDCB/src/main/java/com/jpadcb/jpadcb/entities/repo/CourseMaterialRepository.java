package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Course;
import com.jpadcb.jpadcb.entities.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {


}