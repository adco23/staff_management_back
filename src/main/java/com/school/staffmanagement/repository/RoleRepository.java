package com.school.staffmanagement.repository;

import com.school.staffmanagement.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    List<RoleEntity> findRoleEntitiesByNameIn(List<String> roleNames);
}
