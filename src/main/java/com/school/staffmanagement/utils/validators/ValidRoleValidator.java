package com.school.staffmanagement.utils.validators;

import com.school.staffmanagement.utils.validators.annotation.ValidRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidRoleValidator implements ConstraintValidator<ValidRole, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        List<String> allowedValues = List.of("USER", "ADMIN", "GUEST");
        return role != null && allowedValues.contains(role);
    }
}
