package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.SchoolYearDto;
import com.school.staffmanagement.model.entity.SchoolYear;

import java.util.List;

public interface ISchoolYearService {
    SchoolYear save(SchoolYearDto schoolYearDto);
    SchoolYear findById(Long id);
    void delete(SchoolYear schoolYear);
    boolean existsById(Long id);
}
