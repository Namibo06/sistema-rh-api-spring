package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.LoginResponseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.LoginService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> login(@RequestBody LoginResponseDTO credentials){
        try {

            boolean existsEmployee=service.findEmployeeByLoginAndPassword(credentials);
            if(!existsEmployee){
                throw new EntityNotFoundException("Funcionário não encontrado");
            }

            String tokenCreated = service.createToken();

            ResponseMessageStatus response = service.updateTokenByUser(credentials.login(),tokenCreated);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            String message=e.getMessage();
            Integer status = 404;

            ResponseMessageStatus response = new ResponseMessageStatus(message,status);
            return ResponseEntity.ok(response);
        }
    }
}
