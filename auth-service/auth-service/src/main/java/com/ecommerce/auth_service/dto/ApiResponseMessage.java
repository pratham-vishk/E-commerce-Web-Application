package com.ecommerce.auth_service.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {
    private  String message;
    private  boolean success;
    private HttpStatus status;
}
