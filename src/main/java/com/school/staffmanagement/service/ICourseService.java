package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.CourseDto;
import com.school.staffmanagement.model.dto.response.CourseResponse;
import com.school.staffmanagement.model.entity.Course;

import java.util.List;

public interface ICourseService {
    Course save(CourseDto courseDto);
    Course findById(Long id);
    void delete(Course course);
    boolean existsById(Long id);
    List<CourseResponse> listByInstitution(Long institutionId);
}
