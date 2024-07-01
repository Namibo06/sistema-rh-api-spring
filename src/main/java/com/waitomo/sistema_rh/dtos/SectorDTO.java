package com.waitomo.sistema_rh.dtos;

public record SectorDTO (
        Long id,
        String name,
        Long enterprise_id
) {
    public SectorDTO() {
        this(null, null, null);
    }
}
