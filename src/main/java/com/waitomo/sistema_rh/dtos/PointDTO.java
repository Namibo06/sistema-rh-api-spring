package com.waitomo.sistema_rh.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record PointDTO(
        Long id,
        Long employeeId,
        LocalDate date,
        LocalTime chekInTime,
        LocalTime checkOutLunch,
        LocalTime backLunch,
        LocalTime CheckOutTime
){
}
