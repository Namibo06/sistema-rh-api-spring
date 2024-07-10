package com.waitomo.sistema_rh;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.services.EnterpriseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestEnterprise {
    @Autowired
    private EnterpriseService service;

    @Test
    public void testFindById(){
        Long enterpriseId = 2L;
        EnterpriseDTO result = service.findEnterpriseById(enterpriseId);
        assertNotNull(result.getId());
        System.out.println(result.getId()+"|"+result.getCnpj()+"|"+result.getFantasy_name()+"|"+result.getCompany_name());
    }
}
