package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {
}
