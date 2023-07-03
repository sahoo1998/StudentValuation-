package com.student.repository;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	
}
