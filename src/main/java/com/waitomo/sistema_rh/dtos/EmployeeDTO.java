package com.waitomo.sistema_rh.dtos;

import com.waitomo.sistema_rh.models.EmployeeAddress;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.models.Sector;
import com.waitomo.sistema_rh.models.UserLevel;

import java.time.LocalDate;
import java.util.Date;

public record EmployeeDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateNasciment,
        String gender,
        Long sector,
        String cep,
        String cnpjEnterprise,
        Long userLevel,
        String login,
        String password,
        String token
) {
}
