package com.waitomo.sistema_rh.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_enterprise")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "char(14)",unique = true,nullable = false)
    private String cnpj;
    @Column(nullable = false,length = 25)
    private String fantasy_name;
    @Column(nullable = false,length = 25)
    private String company_name;
    private Integer number_employees;
}
