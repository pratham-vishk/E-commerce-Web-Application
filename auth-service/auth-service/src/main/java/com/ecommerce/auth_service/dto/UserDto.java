package com.ecommerce.auth_service.dto;

import com.ecommerce.auth_service.constant.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private  Integer userId;


    private String name;


    private String phone;


    private String email;

    private  String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;
}
