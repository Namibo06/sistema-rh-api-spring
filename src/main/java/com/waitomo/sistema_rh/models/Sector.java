package com.waitomo.sistema_rh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "tb_sector")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15,nullable = false)
    private String name;
    @Column(name = "enterprise_id",nullable = false)
    private Long enterprise_id;

}
