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
		EnterpriseDTO dto = new EnterpriseDTO();
		dto.setId(null);
		dto.setCnpj("12345678912345");
		dto.setFantasy_name("nome ficticio");
		dto.setCompany_name("nome empresarial");
		dto.setNumber_employee(null);
		ResponseMessageStatus result = enterpriseService.createEnterpriseService(dto);
		assertNotNull(result);
	}

	@Test
	public void testCnpjLength(){
		EnterpriseDTO dto = new EnterpriseDTO();
		dto.setId(null);
		dto.setCnpj("1234567891245");
		dto.setFantasy_name("nome ficticio");
		dto.setCompany_name("nome empresarial");
		dto.setNumber_employee(null);
		assertEquals(dto.getCnpj().length(),14);
	}

}
