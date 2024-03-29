package com.school.staffmanagement.constants;

public class StaffResponseMessages {
    // Mensajes de éxito
    public static final String SUCCESS = "La operación se realizó correctamente";
    public static final String USER_LOGIN_OK = "El usuario inicio sesión correctamente";
    public static final String USER_SIGNUP_OK = "El usuario se creó correctamente";

    // Mensajes de error general
    public static final String INTERNAL_SERVER_ERROR = "Ha ocurrido un error interno en el servidor";

    // Mensajes de error de validación
    public static final String VALIDATION_ERROR = "La solicitud contiene datos no válidos";
    public static final String EMAIL_IS_REQUIRED = "El correo es obligatoio";
    public static final String PASSWORD_IS_REQUIRED = "La contraseña es obligatoria";
    public static final String ROLE_IS_INVALID = "Rol/s inválido/s";
    public static final String ROLE_IS_REQUIRED = "El rol es obligatorio";

    // Mensajes de error específicos
    public static final String RESOURCE_NOT_FOUND = "El recurso solicitado no se ha encontrado";
    public static final String TOKEN_INVALID = "Token inválido, no autorizado";
    public static final String BAD_CREDENTIALS = "Correo o contraseña invalido";
    public static final String USER_NOT_FOUND = "El usuario %s no existe";



    public static String formatMessage(String message, String data) {
        return String.format(message, data);
    }

    public static String userNotFoundMsg(String data) {
        return String.format(USER_NOT_FOUND, data);
    }
}
