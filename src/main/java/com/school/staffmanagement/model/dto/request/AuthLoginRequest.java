package com.school.staffmanagement.model.dto.request;

import com.school.staffmanagement.constants.StaffResponseMessages;
import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
        @NotBlank(message = StaffResponseMessages.EMAIL_IS_REQUIRED) String email,
        @NotBlank(message = StaffResponseMessages.PASSWORD_IS_REQUIRED) String password
) {
}
