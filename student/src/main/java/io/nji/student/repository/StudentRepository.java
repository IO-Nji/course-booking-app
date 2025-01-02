package io.nji.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nji.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
