package com.school.staffmanagement.service.impl;

import com.school.staffmanagement.model.dto.ProfileDto;
import com.school.staffmanagement.model.entity.Profile;
import com.school.staffmanagement.repository.ProfileRepository;
import com.school.staffmanagement.service.IProfileService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProfileImplService implements IProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    @Override
    public Profile save(ProfileDto profileDto) {
        Profile profile = Profile.builder()
                .id(profileDto.getId())
                .firstName(profileDto.getFirstName())
                .lastName(profileDto.getLastName())
                .cuil(profileDto.getCuil())
                .address(profileDto.getAddress())
                .birthdate(profileDto.getBirthdate())
                .phone(profileDto.getPhone())
                .degree(profileDto.getDegree())
                .degreeReg(profileDto.getDegreeReg())
                .medicalCondition(profileDto.getMedicalCondition())
                .emergencyPerson(profileDto.getEmergencyPerson())
                .emergencyPhone(profileDto.getEmergencyPhone())
                .build();

        return profileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }

    @Override
    public List<Profile> listAll() {
        return (List) profileRepository.findAll();
    }
}
