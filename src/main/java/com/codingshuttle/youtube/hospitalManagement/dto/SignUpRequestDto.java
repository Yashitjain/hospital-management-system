package com.codingshuttle.youtube.hospitalManagement.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequestDto {
    private String userName;
    private String password;
}
