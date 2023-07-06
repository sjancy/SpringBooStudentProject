package com.jancy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jancy.springboot.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
