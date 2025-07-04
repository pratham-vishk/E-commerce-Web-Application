package com.ecommerce.auth_service.service;

import com.ecommerce.auth_service.dto.AuthenticationRequest;
import com.ecommerce.auth_service.dto.AuthenticationResponse;
import com.ecommerce.auth_service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
    AuthenticationResponse signUp(UserDto userDto);
    AuthenticationResponse signIn(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
