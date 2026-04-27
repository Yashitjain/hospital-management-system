package com.codingshuttle.youtube.hospitalManagement.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpResponseDto {
    private String userName;
    private long userId;

}

