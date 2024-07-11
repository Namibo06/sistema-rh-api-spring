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
    Long id;
    String firstName;
    String lastName;
    LocalDate dateNasciment;
    String gender;
    Long sector;
    String cep;
    String cnpjEnterprise;
    Long userLevel;
    String login;
    String password;
    String token;
}
