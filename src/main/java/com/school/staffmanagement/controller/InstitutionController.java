package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.BadRequestException;
import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.dto.InstitutionDto;
import com.school.staffmanagement.model.entity.Client;
import com.school.staffmanagement.model.entity.Institution;
import com.school.staffmanagement.model.payload.MessageResponse;
import com.school.staffmanagement.service.IInstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/institutions")
public class InstitutionController {
    @Autowired
    private IInstitutionService institutionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        try {
            if (!institutionService.existsById(id)) {
                throw new ResourceNotFoundException("institution", "id", id);
            }

            Institution institution = institutionService.findById(id);

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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid InstitutionDto request, @PathVariable Long id) {
        Institution institution = null;
        try {
            if (!institutionService.existsById(id)) {
                throw new ResourceNotFoundException("institution", "id", id);
            }

            request.setId(id);

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Institution institution = institutionService.findById(id);
            institutionService.delete(institution);
            return new ResponseEntity<>(institution, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
