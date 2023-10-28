package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.BadRequestException;
import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.dto.CourseDto;
import com.school.staffmanagement.model.dto.InstitutionDto;
import com.school.staffmanagement.model.dto.response.CourseResponse;
import com.school.staffmanagement.model.entity.Institution;
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
@RequestMapping("/api/v1/institutions")
public class InstitutionController {
    @Autowired
    private IInstitutionService institutionService;

    @Autowired
    private ICourseService courseService;

    @GetMapping("/{institutionId}")
    public ResponseEntity<?> showById(@PathVariable Long institutionId) {
        try {
            if (!institutionService.existsById(institutionId)) {
                throw new ResourceNotFoundException("institution", "id", institutionId);
            }

            Institution institution = institutionService.findById(institutionId);

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("OK")
                    .data(InstitutionDto.builder()
                            .id(institution.getId())
                            .name(institution.getName())
                            .address(institution.getAddress())
                            .phone(institution.getPhone())
                            .build()
                    ).build(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{institutionId}/courses")
    public ResponseEntity<?> showCourses(@PathVariable Long institutionId) {
        List<CourseResponse> coursesList = courseService.listByInstitution(institutionId);

        if (coursesList == null || coursesList.isEmpty()) {
            throw new ResourceNotFoundException("courses", "institution ID", institutionId);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("OK")
                        .data(coursesList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid InstitutionDto request) {
        Institution institution = null;
        try {
            institution = institutionService.save(request);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("OK")
                    .data(InstitutionDto.builder()
                            .id(institution.getId())
                            .name(institution.getName())
                            .address(institution.getAddress())
                            .phone(institution.getPhone())
                            .build())
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{institutionId}")
    public ResponseEntity<?> update(@RequestBody @Valid InstitutionDto request, @PathVariable Long institutionId) {
        Institution institution = null;
        try {
            if (!institutionService.existsById(institutionId)) {
                throw new ResourceNotFoundException("institution", "id", institutionId);
            }

            request.setId(institutionId);

            institution = institutionService.save(request);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("OK")
                    .data(InstitutionDto.builder()
                            .id(institution.getId())
                            .name(institution.getName())
                            .address(institution.getAddress())
                            .phone(institution.getPhone())
                            .build())
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{institutionId}")
    public ResponseEntity<?> delete(@PathVariable Long institutionId) {
        try {
            Institution institution = institutionService.findById(institutionId);
            institutionService.delete(institution);
            return new ResponseEntity<>(institution, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
