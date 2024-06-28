package com.waitomo.sistema_rh.dtos;

public record EnterpriseDTO(
        Long id,
        String cnpj,
        String fantasy_name,
        String company_name,
        Integer number_employees
) { }
