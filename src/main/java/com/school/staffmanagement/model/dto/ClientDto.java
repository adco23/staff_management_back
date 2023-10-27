package com.school.staffmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.staffmanagement.annotations.EnumValue;
import com.school.staffmanagement.model.enums.ClientRolEnum;
import com.school.staffmanagement.model.enums.ClientStatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
public class ClientDto implements Serializable {

    private Long id;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String rol;

    private String status;

    @JsonIgnore
    private Date createdAt;

    @JsonProperty
    public Date getCreatedAt() {
        return createdAt;
    }
}

