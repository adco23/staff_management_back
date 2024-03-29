package com.school.staffmanagement.constants;

public class StaffResponseMessages {
    // Mensajes de éxito
    public static final String SUCCESS = "Operation successful";
    public static final String USER_LOGIN_OK = "User logged successfully";
    public static final String USER_SIGNUP_OK = "User created successfully";

    // Mensajes de error general
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";

    // Mensajes de error de validación
    public static final String VALIDATION_ERROR = "Request has invalid data";
    public static final String EMAIL_IS_REQUIRED = "Email is required";
    public static final String PASSWORD_IS_REQUIRED = "Password is required";
    public static final String ROLE_IS_INVALID = "Invalid role/s";
    public static final String ROLE_IS_REQUIRED = "Rol is required";

    // Mensajes de error específicos
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String TOKEN_INVALID = "Invalid token, not authorized";
    public static final String BAD_CREDENTIALS = "Invalid email or password";
    public static final String USER_NOT_FOUND = "User %s not found";



    public static String formatMessage(String message, String data) {
        return String.format(message, data);
    }

    public static String userNotFoundMsg(String data) {
        return String.format(USER_NOT_FOUND, data);
    }
}
