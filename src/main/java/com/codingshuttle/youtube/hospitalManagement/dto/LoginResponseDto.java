package com.codingshuttle.youtube.hospitalManagement.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    String jwt;
    Long userId;
}
