package com.waitomo.sistema_rh;

import com.waitomo.sistema_rh.dtos.EmployeeAddressDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.repositories.EmployeeAddressRepository;
import com.waitomo.sistema_rh.services.ViacepService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TestEmployeeAddress {
    @Autowired
    private ViacepService service;

    @Test
    public void verifyCep(){
        String cep = "44095400";
        ResponseMessageStatus result = service.verifyAddressService(cep);
        assertEquals(result.status(),200);
    }
}
