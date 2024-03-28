package com.school.staffmanagement.model.dto.response;

import lombok.*;

@Data
@Builder
@ToString
public class ResponseMessage {
    public Integer code;
    public String message;
}
