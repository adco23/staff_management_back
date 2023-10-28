package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.CourseDto;
import com.school.staffmanagement.model.entity.Course;

public interface ICourseService {
    Course save(CourseDto courseDto);
    Course findById(Long id);
    void delete(Course course);
    boolean existsById(Long id);
}
