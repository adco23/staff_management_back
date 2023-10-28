package com.school.staffmanagement.model.dto.response;

import com.school.staffmanagement.model.entity.Institution;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class CourseResponse implements Serializable {
    private Long id;
    private String course;
    private String division;
    private String shift;
    private String title;
    private String institution;
}
