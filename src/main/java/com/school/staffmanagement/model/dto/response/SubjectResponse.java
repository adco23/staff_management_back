package com.school.staffmanagement.model.dto.response;

import com.school.staffmanagement.model.entity.Course;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class SubjectResponse implements Serializable {
    private Long id;
    private String title;
    private CourseResponse course;
}
