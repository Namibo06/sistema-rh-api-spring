package com.waitomo.sistema_rh.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PointDTO{
    Long id;
    Long employeeId;
    LocalDate date;
    LocalTime checkInTime;
    LocalTime checkOutLunch;
    LocalTime backLunch;
    LocalTime checkOutTime;
}
