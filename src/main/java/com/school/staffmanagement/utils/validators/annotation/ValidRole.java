package com.school.staffmanagement.utils.validators.annotation;

import com.school.staffmanagement.constants.StaffResponseMessages;
import com.school.staffmanagement.utils.validators.ValidRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidRoleValidator.class)
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default StaffResponseMessages.ROLE_IS_INVALID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
