package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.ProfileDto;
import com.school.staffmanagement.model.entity.Profile;

import java.util.List;

public interface IProfileService {
    Profile save(ProfileDto profileDto);
    Profile findById(Long id);
    void delete(Profile profile);
    List<Profile> listAll();
}
