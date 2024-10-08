package com.harshitbhardwaj.student_rest_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshitbhardwaj.student_rest_api.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByName(String name);

	Optional<Student> findByEmail(String email);

}
