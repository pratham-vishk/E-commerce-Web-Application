package com.ecommerce.auth_service.controller;
import com.ecommerce.auth_service.dto.AuthenticationRequest;
import com.ecommerce.auth_service.dto.AuthenticationResponse;
import com.ecommerce.auth_service.dto.UserDto;
import com.ecommerce.auth_service.entity.User;
import com.ecommerce.auth_service.exception.ResourceNotFoundException;
import com.ecommerce.auth_service.repository.UserRepository;
import com.ecommerce.auth_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signUp")
    public ResponseEntity<AuthenticationResponse> signUp(
            @Valid @RequestBody UserDto userDto
    ) {
        return new ResponseEntity<>(authService.signUp(userDto), HttpStatus.OK);
    }
    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> signIn(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return new ResponseEntity<>(authService.signIn(request), HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }
}

