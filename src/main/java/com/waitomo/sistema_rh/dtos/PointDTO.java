package com.waitomo.sistema_rh.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PointDTO{
    private Long employeeId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutLunch;
    private LocalTime backLunch;
    private LocalTime checkOutTime;
}
