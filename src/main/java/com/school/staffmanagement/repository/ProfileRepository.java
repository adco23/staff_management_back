package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
