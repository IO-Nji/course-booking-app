package io.nji.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nji.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
