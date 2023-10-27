package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Institution;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {
}
