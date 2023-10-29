package com.school.staffmanagement.service;

import com.school.staffmanagement.model.entity.Subject;

public interface ISubjectService {
    Subject save(Subject subject);
    Subject findById(Long id);
    void delete(Subject subject);
    boolean existsByTitleAndCourse(String title, Long idCourse);
    boolean existsById(Long id);
}
