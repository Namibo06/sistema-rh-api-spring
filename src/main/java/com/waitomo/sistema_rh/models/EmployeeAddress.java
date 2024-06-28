package com.waitomo.sistema_rh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_employee_address")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeAddress {
    @Id
    @Column(columnDefinition = "char(8)")
    private String cep;
    @Column(nullable = false,columnDefinition = "char(2)")
    private String uf;
    @Column(nullable = false,length = 25)
    private String city;
    @Column(length = 25)
    private String neighborhood;
    @Column(length = 25)
    private String road;
}
