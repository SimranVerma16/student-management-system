package com.guruji.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.guruji.studentmanagement.entity.StudentEntity;

public interface StudentRepository 
        extends JpaRepository<StudentEntity, Long> {

}