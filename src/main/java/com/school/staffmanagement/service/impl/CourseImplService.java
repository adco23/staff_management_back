package com.school.staffmanagement.service.impl;

import com.school.staffmanagement.model.dto.CourseDto;
import com.school.staffmanagement.model.dto.response.CourseResponse;
import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.entity.Institution;
import com.school.staffmanagement.repository.CourseRepository;
import com.school.staffmanagement.repository.InstitutionRepository;
import com.school.staffmanagement.service.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CourseImplService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Transactional
    @Override
    public Course save(CourseDto courseDto) {
        if (courseDto.getId() != null && courseRepository.existsById(courseDto.getId())) {
            Course existing = courseRepository.findById(courseDto.getId()).orElse(null);
            Course course = Course.builder()
                    .id(existing.getId())
                    .course(courseDto.getCourse())
                    .division(courseDto.getDivision())
                    .shift(courseDto.getShift())
                    .title(courseDto.getTitle())
                    .institution(existing.getInstitution())
                    .build();
            return courseRepository.save(course);
        }

        Institution institution = institutionRepository.findById(courseDto.getInstitution()).orElse(null);

        Course course = Course.builder()
                .id(courseDto.getId())
                .course(courseDto.getCourse())
                .division(courseDto.getDivision())
                .shift(courseDto.getShift())
                .title(courseDto.getTitle())
                .institution(institution)
                .build();
        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CourseResponse> listByInstitution(Long institutionId) {
        List<Course> list = courseRepository.findByInstitutionId(institutionId);

        List<CourseResponse> listDto = new ArrayList<>();

        for (Course course: list) {
            CourseResponse courseResponse = CourseResponse.builder()
                    .id(course.getId())
                    .course(course.getCourse())
                    .division(course.getDivision())
                    .title((course.getTitle() != null) ? course.getTitle() : "")
                    .shift(course.getShift())
                    .institution(course.getInstitution().getName())
                    .build();

            listDto.add(courseResponse);
        }

        return listDto;
    }
}
