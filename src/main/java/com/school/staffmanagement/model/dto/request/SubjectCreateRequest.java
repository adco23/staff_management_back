package com.school.staffmanagement.model.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class SubjectCreateRequest implements Serializable {
    private String title;
    private Long course;
}
