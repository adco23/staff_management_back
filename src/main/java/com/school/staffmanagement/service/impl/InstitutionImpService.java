package com.school.staffmanagement.service.impl;

import com.school.staffmanagement.model.dto.InstitutionDto;
import com.school.staffmanagement.model.entity.Institution;
import com.school.staffmanagement.repository.InstitutionRepository;
import com.school.staffmanagement.service.IInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstitutionImpService implements IInstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Transactional
    @Override
    public Institution save(InstitutionDto institutionDto) {
        Institution institution = Institution.builder()
                .id(institutionDto.getId())
                .name(institutionDto.getName())
                .address(institutionDto.getAddress())
                .phone(institutionDto.getPhone())
                .build();
        return institutionRepository.save(institution);
    }

    @Transactional(readOnly = true)
    @Override
    public Institution findById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Institution institution) {
        institutionRepository.delete(institution);
    }

    @Override
    public boolean existsById(Long id) {
        return institutionRepository.existsById(id);
    }
}
