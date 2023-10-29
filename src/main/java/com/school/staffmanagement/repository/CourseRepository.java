package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstitutionId(Long institutionId);
    boolean existsByCourseAndDivisionAndShiftAndTitleAndInstitution(String course, String division, String shift, String title, Institution institution);
    Course findByCourseAndDivisionAndShiftAndTitleAndInstitution(String course, String division, String shift, String title, Institution institution);
}
