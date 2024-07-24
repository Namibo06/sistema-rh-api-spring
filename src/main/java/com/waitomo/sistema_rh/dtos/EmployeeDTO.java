package com.waitomo.sistema_rh.dtos;

import com.waitomo.sistema_rh.models.EmployeeAddress;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.models.Sector;
import com.waitomo.sistema_rh.models.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDTO{
    private String firstName;
    private String lastName;
    private LocalDate dateNasciment;
    private String gender;
    private Long sector;
    private String cep;
    private String cnpjEnterprise;
    private Long userLevel;
    private String login;
    private String password;
    private String token;
}
