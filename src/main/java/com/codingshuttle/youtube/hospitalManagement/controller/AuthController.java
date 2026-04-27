package com.codingshuttle.youtube.hospitalManagement.controller;

import com.codingshuttle.youtube.hospitalManagement.Security.AuthService;
import com.codingshuttle.youtube.hospitalManagement.Security.CustomUserDetailsService;
import com.codingshuttle.youtube.hospitalManagement.dto.LoginRequestDto;
import com.codingshuttle.youtube.hospitalManagement.dto.LoginResponseDto;
import com.codingshuttle.youtube.hospitalManagement.dto.SignUpRequestDto;
import com.codingshuttle.youtube.hospitalManagement.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService customUserDetailsService;
    private final AuthService authService;

    @Autowired
    public AuthController(CustomUserDetailsService customUserDetailsService, AuthService authService){
        this.customUserDetailsService = customUserDetailsService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> createLogin(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginResponseDto  = authService.login(loginRequestDto);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> createSignup(@RequestBody SignUpRequestDto signUpRequestDto){
        SignUpResponseDto signUpResponseDto  = authService.signup(signUpRequestDto);

        return ResponseEntity.ok(signUpResponseDto);
    }
}
