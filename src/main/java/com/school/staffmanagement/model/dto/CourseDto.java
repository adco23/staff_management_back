package com.school.staffmanagement.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class CourseDto implements Serializable {
    private Long id;
    @NotBlank(message = "Course is required")
    private String course;
    private String division;
    private String shift;
    private String title;
    @NotNull(message = "Institution ID is required")
    private Long institution;
}
