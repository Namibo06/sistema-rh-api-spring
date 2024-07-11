package com.waitomo.sistema_rh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_point")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="employee_id")
    private Long employeeId;
    @Column(nullable = false)
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutLunch;
    private LocalTime backLunch;
    private LocalTime checkOutTime;
}
