package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByTitleAndCourse(String title, Course course);
}
