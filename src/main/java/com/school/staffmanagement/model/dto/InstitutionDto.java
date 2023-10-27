package com.school.staffmanagement.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class InstitutionDto implements Serializable {
    private Long id;

    @NotEmpty(message = "Institution name is required")
    private String name;

    private String address;

    private String phone;
}
