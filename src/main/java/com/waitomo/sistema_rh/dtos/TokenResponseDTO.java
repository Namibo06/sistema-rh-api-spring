package com.waitomo.sistema_rh.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TokenResponseDTO {
    String token;
    String message;
    Integer status;
}
