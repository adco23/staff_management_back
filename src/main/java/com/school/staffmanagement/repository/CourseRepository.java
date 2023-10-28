package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstitutionId(Long institutionId);
}
