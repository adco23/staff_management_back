package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.BadRequestException;
import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.dto.ClientDto;
import com.school.staffmanagement.model.dto.request.SubjectCreateRequest;
import com.school.staffmanagement.model.dto.request.SubjectUpdateRequest;
import com.school.staffmanagement.model.dto.response.CourseResponse;
import com.school.staffmanagement.model.dto.response.SubjectResponse;
import com.school.staffmanagement.model.entity.Client;
import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.entity.Subject;
import com.school.staffmanagement.model.payload.MessageResponse;
import com.school.staffmanagement.service.ICourseService;
import com.school.staffmanagement.service.ISubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private ICourseService courseService;

    @GetMapping("/{idSubject}")
    public ResponseEntity<?> showById(@PathVariable Long idSubject) {
        try {
            Subject subject = subjectService.findById(idSubject);

            if (subject == null) {
                throw new ResourceNotFoundException("subject", "id", idSubject);
            }

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("OK")
                    .data(SubjectResponse.builder()
                            .id(subject.getId())
                            .title(subject.getTitle())
                            .course(CourseResponse.builder()
                                    .id(subject.getCourse().getId())
                                    .course(subject.getCourse().getCourse())
                                    .division(subject.getCourse().getDivision())
                                    .shift(subject.getCourse().getShift())
                                    .title(subject.getCourse().getTitle())
                                    .institution(subject.getCourse().getInstitution().getName())
                                    .build())
                            .build())
                    .build(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody SubjectCreateRequest request) {
        try {
            if (subjectService.existsByTitleAndCourse(request.getTitle(), request.getCourse())) {
                throw new BadRequestException("Subject already exists");
            }

            Course course = courseService.findById(request.getCourse());

            Subject subject = new Subject();

            subject.setTitle(request.getTitle());
            subject.setCourse(course);

            subject = subjectService.save(subject);

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Subject saved")
                    .data(SubjectResponse.builder()
                            .id(subject.getId())
                            .title(subject.getTitle())
                            .course(CourseResponse.builder()
                                    .id(subject.getCourse().getId())
                                    .course(subject.getCourse().getCourse())
                                    .division(subject.getCourse().getDivision())
                                    .shift(subject.getCourse().getShift())
                                    .title(subject.getCourse().getTitle())
                                    .institution(subject.getCourse().getInstitution().getName())
                                    .build())
                            .build()
                    )
                    .build(), HttpStatus.CREATED);

        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{idSubject}")
    public ResponseEntity<?> update(@Valid @RequestBody SubjectUpdateRequest request, @PathVariable Long idSubject) {
        try {
            if (subjectService.existsById(request.getId())) {
                request.setId(idSubject);

                Course course = courseService.findById(request.getCourse());

                Subject subject = Subject.builder()
                        .id(idSubject)
                        .title(request.getTitle())
                        .course(course)
                        .build();

                subject = subjectService.save(subject);

                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Client saved")
                        .data(SubjectResponse.builder()
                                .id(subject.getId())
                                .title(subject.getTitle())
                                .course(CourseResponse.builder()
                                        .id(subject.getCourse().getId())
                                        .course(subject.getCourse().getCourse())
                                        .division(subject.getCourse().getDivision())
                                        .shift(subject.getCourse().getShift())
                                        .title(subject.getCourse().getTitle())
                                        .institution(subject.getCourse().getInstitution().getName())
                                        .build())
                                .build()
                        )
                        .build(), HttpStatus.CREATED);
            } else {
                throw new ResourceNotFoundException("subject", "id", idSubject);
            }
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{idSubject}")
    public ResponseEntity<?> delete(@PathVariable Long idSubject) {
        try {
            Subject subject = subjectService.findById(idSubject);
            subjectService.delete(subject);
            return new ResponseEntity<>(subject, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
