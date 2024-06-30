package com.waitomo.sistema_rh;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.LoginResponseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EmployeeService;
import com.waitomo.sistema_rh.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestLogin {
    @Autowired
    private LoginService service;

    @Test
    public void testCreateToken(){
        LoginResponseDTO credentials = new LoginResponseDTO("teste@gmail.com","123");
        String result = service.createToken();
        assertNotNull(result);
    }

    @Test
    public void testUpdateToken(){
        LoginResponseDTO credentials = new LoginResponseDTO("teste@gmail.com","123");
        String token = service.createToken();
        ResponseMessageStatus response = service.updateTokenByUser("teste@gmail.com",token);
        assertNotNull(response);
    }

    @Test
    public void testCheckEmployee(){
        LoginResponseDTO credentials = new LoginResponseDTO("teste@gmail.com","123");
        boolean result = service.findEmployeeByLoginAndPassword(credentials);
        assertNotNull(result);
    }
}
