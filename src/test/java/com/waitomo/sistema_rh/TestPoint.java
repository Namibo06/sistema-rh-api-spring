package com.waitomo.sistema_rh;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.PointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TestPoint {
    @Autowired
    private PointService service;

    @Test
    public void testCreatePoint(){
        PointDTO pointDTO = new PointDTO(null,1L, LocalDate.of(2024,06,29), LocalTime.of(9,00),LocalTime.of(12,00),LocalTime.of(14,00),LocalTime.of(16,00));
        ResponseMessageStatus result = service.createPointService(pointDTO);
        assertNotNull(result);
    }
}
