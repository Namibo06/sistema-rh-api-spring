package com.waitomo.sistema_rh.dtos;

public record EmployeeAddressDTO (
        String cep,
        String uf,
        String city,
        String neighborhood,
        String road
){
    public EmployeeAddressDTO() {
        this( null,null,null,null,null);
    }
}
