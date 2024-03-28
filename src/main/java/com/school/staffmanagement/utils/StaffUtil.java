package com.school.staffmanagement.utils;

import com.school.staffmanagement.model.dto.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StaffUtil {
    private StaffUtil() {}

    public static ResponseEntity<ResponseMessage> getResponseEntity(String message, Integer code, HttpStatus status) {
        return new ResponseEntity<ResponseMessage>(ResponseMessage.builder()
                .code(code)
                .message(message)
                .build(), status
        );
    }

    public static ResponseEntity<ResponseMessage> getResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<ResponseMessage>(ResponseMessage.builder()
                .code(status.value())
                .message(message)
                .build(), status
        );
    }
}
