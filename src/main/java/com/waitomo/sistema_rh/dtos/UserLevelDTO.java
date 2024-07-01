package com.waitomo.sistema_rh.dtos;

public record UserLevelDTO(
        Long id,
        String name,
        Long enterprise_id
) {
    public UserLevelDTO() {
        this(null, null, null);
    }
}
