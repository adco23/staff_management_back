package com.school.staffmanagement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"email", "message", "roles", "jwt", "status"})
public record AuthResponse(String email,
                           String message,
                           List<String> roles,
                           String jwt) {
}
