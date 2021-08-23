package com.jpadcb.jpadcb.entities.repo;

import com.jpadcb.jpadcb.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public List<Student> findByFirstName(String firstName);
    public  List<Student> findByFirstNameContaining(String name);
    public  List<Student> findByLastNameNotNull();
    public  List<Student>  findByGuardianName(String gname);
    Student findByFirstNameAndLastName(String firstName,String lastName);

    //using jpql
    @Query("select  s from Student  s where  s.emailId=?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select  s.firstName from Student  s where  s.emailId=?1")
    Student getNameByEmailAddress(String emailId);

    //native sql query
    @Query(value = "select * from dcb.tbl_student s where  s.email_address=?1",nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);
    //native sql query
    @Query(value = "select * from dcb.tbl_student s where  s.email_address=:email_address",nativeQuery = true)
    Student getStudentByEmailAddressNativeParam(@Param("email_address") String emailId);

    @Modifying
    @Transactional //allows all transactions to occur and commit to database//
                    //error occurs InvalidDataAccessApiUsageException if transactional is not added
    @Query(
            value = "update dcb.tbl_student set first_name=:fname where email_address=:emailId",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(@Param("fname") String name, @Param("emailId") String emailId);

}