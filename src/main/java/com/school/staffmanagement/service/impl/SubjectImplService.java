package com.school.staffmanagement.service.impl;

import com.school.staffmanagement.model.entity.Course;
import com.school.staffmanagement.model.entity.Subject;
import com.school.staffmanagement.repository.CourseRepository;
import com.school.staffmanagement.repository.SubjectRepository;
import com.school.staffmanagement.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectImplService implements ISubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional(readOnly = true)
    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByTitleAndCourse(String title, Long idCourse) {
        Course course = courseRepository.findById(idCourse).orElse(null);
        return subjectRepository.existsByTitleAndCourse(title, course);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long idSubject) {
        return subjectRepository.existsById(idSubject);
    }
}
