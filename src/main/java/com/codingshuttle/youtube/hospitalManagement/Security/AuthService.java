package com.codingshuttle.youtube.hospitalManagement.Security;

import com.codingshuttle.youtube.hospitalManagement.dto.LoginRequestDto;
import com.codingshuttle.youtube.hospitalManagement.dto.LoginResponseDto;
import com.codingshuttle.youtube.hospitalManagement.dto.SignUpRequestDto;
import com.codingshuttle.youtube.hospitalManagement.dto.SignUpResponseDto;
import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService  {

    private final AuthenticationManager authenticationManager;
    private final AuthUtils authUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    AuthService(AuthenticationManager authenticationManager, AuthUtils authUtils, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.authUtils = authUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String jwt = authUtils.generateToken(user);

        return LoginResponseDto.builder()
                .jwt(jwt)
                .userId(user.getId())
                .build();

    }

    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {
        Optional<User> userNameExist = userRepository.findByUsername(signUpRequestDto.getUserName());
        if(userNameExist.isPresent()){
            throw new IllegalArgumentException("UserName Already Taken");
        }

        User user = User.builder()
                .username(signUpRequestDto.getUserName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();
        user = userRepository.save(user);

        return SignUpResponseDto.builder()
                .userName(user.getUsername())
                .userId(user.getId())
                .build();
    }
}
