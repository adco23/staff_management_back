package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
