package com.school.staffmanagement.controller;

import com.school.staffmanagement.exception.ResourceNotFoundException;
import com.school.staffmanagement.model.entity.Profile;
import com.school.staffmanagement.model.payload.MessageResponse;
import com.school.staffmanagement.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    @Autowired
    private IProfileService profileService;

    @GetMapping("")
    public ResponseEntity<?> showAll() {
        List<Profile> list = profileService.listAll();
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("profiles");
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("OK")
                        .data(list)
                        .build()
                , HttpStatus.OK);
    }
}
