package com.waitomo.sistema_rh.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterpriseDTO{
    private Long id;
    private String cnpj;
    private String fantasy_name;
    private String company_name;
    private Integer number_employees;
}
