package com.school.staffmanagement.model.dto.request;

import com.school.staffmanagement.constants.StaffResponseMessages;
import com.school.staffmanagement.utils.validators.annotation.ValidRole;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AuthCreateUserRequest(
        @NotBlank(message = StaffResponseMessages.EMAIL_IS_REQUIRED) String email,
        @NotBlank(message = StaffResponseMessages.PASSWORD_IS_REQUIRED) String password,

        List<@ValidRole String> roles
) {}
