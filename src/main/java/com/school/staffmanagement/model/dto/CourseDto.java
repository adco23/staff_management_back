package com.school.staffmanagement.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class CourseDto implements Serializable {
    private Long id;
    private String course;
    private String division;
    private String shift;
    private String title;
    private Long institution;

    public void setTitle(String title) {
        this.title = (title != null) ? title : "";
    }
}
