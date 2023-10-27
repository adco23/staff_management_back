package com.school.staffmanagement.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
public class ProfileDto implements Serializable {
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "CUIL is required")
    private String cuil;
    private String address;
    private Date birthdate;
    @NotBlank(message = "Phone number is required")
    private String phone;
    @NotBlank(message = "Degree is required")
    private String degree;
    @NotBlank(message = "Degree Registration is required")
    private String degreeReg;
    private String medicalCondition;
    private String emergencyPerson;
    private String emergencyPhone;
}
