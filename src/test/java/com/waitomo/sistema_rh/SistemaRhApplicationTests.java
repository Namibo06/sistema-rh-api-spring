package com.waitomo.sistema_rh;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EnterpriseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SistemaRhApplicationTests {
	@Autowired
	private EnterpriseService enterpriseService;

	@Test
	public void testCreateEnterprise(){
		EnterpriseDTO dto = new EnterpriseDTO(null, "12345678912345", "nome ficticio", "nome empresarial", null);
		ResponseMessageStatus result = enterpriseService.createEnterpriseService(dto);
		assertNotNull(result);
	}

	@Test
	public void testCnpjLength(){
		EnterpriseDTO dto = new EnterpriseDTO(null, "1234567891245", "nome ficticio", "nome empresarial", null);
		assertEquals(dto.cnpj().length(),14);
	}

}
