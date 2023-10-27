package com.school.staffmanagement.model.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Date time = new Date();
    private String url;
    private Object message;

    public ApiResponse(Object message, String url) {
        this.url = url.replace("uri=", "");
        this.message = message;
    }
}
