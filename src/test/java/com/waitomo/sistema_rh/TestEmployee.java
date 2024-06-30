package com.waitomo.sistema_rh;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.*;
import com.waitomo.sistema_rh.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestEmployee {
    @Autowired
    private EmployeeService service;

    public Sector getMockSector() {
        Sector sector = new Sector();
        sector.setId(1L);
        sector.setName("T.I");
        return sector;
    }

    public EmployeeAddress getMockEmployeeAddress() {
        EmployeeAddress address = new EmployeeAddress();
        address.setCep("44095400");
        return address;
    }

    public Enterprise getMockEnterprise() {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(1L);
        enterprise.setCnpj("11111111111111");
        return enterprise;
    }

    public UserLevel getMockUserLevel() {
        UserLevel userLevel = new UserLevel();
        userLevel.setId(1L);
        userLevel.setName("Admin");
        return userLevel;
    }

    @Test
    public void testCreateEmployee(){
        EmployeeDTO employeeDTO = new EmployeeDTO(null,"Joca",null, LocalDate.of(2003,10,06),"masculino",getMockSector().getId(),getMockEmployeeAddress().getCep(),getMockEnterprise().getCnpj(),getMockUserLevel().getId(),"teste@gmail.com","123","null");
        ResponseMessageStatus result = service.createEmployeeService(employeeDTO);
        assertNotNull(result);
    }
}
