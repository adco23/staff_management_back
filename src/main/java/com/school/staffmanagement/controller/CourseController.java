package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.BadRequestException;
import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.dto.ClientDto;
import com.school.staffmanagement.model.dto.CourseDto;
import com.school.staffmanagement.model.dto.InstitutionDto;
import com.school.staffmanagement.model.dto.response.CourseResponse;
import com.school.staffmanagement.model.entity.Client;
import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.entity.Institution;
import com.school.staffmanagement.model.entity.Subject;
import com.school.staffmanagement.model.payload.MessageResponse;
import com.school.staffmanagement.service.ICourseService;
import com.school.staffmanagement.service.IInstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IInstitutionService institutionService;

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

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CourseDto request) {
        if (request.getDivision() == null) { request.setDivision(""); }
        if (request.getShift() == null) { request.setShift(""); }
        if (request.getTitle() == null) { request.setTitle(""); }
        try {
            Institution institution = institutionService.findById(request.getInstitution());

            if (institutionService.existsCourseInInstitution( request.getCourse(), request.getDivision(),
                    request.getShift(), request.getTitle(), institution )) {
                throw new BadRequestException("Course already exists");
            }

            Course course = courseService.save(request);

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("OK")
                    .data(CourseResponse.builder()
                            .id(course.getId())
                            .course(course.getCourse())
                            .division(course.getDivision())
                            .shift(course.getShift())
                            .title(course.getTitle())
                            .institution(course.getInstitution().getName())
                            .build())
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
