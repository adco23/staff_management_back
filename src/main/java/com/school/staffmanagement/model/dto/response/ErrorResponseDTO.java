package com.school.staffmanagement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Setter;

@JsonPropertyOrder({"code", "message"})
public record ErrorResponseDTO(Integer code, String message) {
}
