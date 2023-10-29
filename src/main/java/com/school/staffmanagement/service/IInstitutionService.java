package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.InstitutionDto;
import com.school.staffmanagement.model.entity.Institution;

public interface IInstitutionService {
    Institution save(InstitutionDto institutionDto);
    Institution findById(Long id);
    void delete(Institution institution);
    boolean existsById(Long id);
    boolean existsCourseInInstitution(String course, String division, String shift, String title, Institution institution);
}
