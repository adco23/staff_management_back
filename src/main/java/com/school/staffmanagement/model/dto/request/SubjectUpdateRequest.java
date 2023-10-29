package com.school.staffmanagement.model.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class SubjectUpdateRequest implements Serializable {
    private Long id;
    private String title;
    private Long course;
}
