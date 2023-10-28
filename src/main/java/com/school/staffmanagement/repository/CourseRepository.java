package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
