package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.BadRequestException;
import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.dto.response.CourseResponse;
import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.payload.MessageResponse;
import com.school.staffmanagement.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<?> showById( @PathVariable Long id) {
        try {
            Course course = courseService.findById(id);

            if (course == null) {
                throw new ResourceNotFoundException("course", "id", id);
            }

            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("OK")
                            .data(CourseResponse.builder()
                                    .id(course.getId())
                                    .course(course.getCourse())
                                    .division(course.getDivision())
                                    .shift(course.getShift())
                                    .title(course.getTitle())
                                    .institution(course.getInstitution().getName())
                                    .build())
                            .build()
                    , HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
