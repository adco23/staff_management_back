package com.school.staffmanagement.service;

import com.school.staffmanagement.model.dto.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<ResponseMessage> signUp(Map<String, String> requestMap);
}
