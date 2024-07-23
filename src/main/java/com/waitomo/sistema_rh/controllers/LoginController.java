package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.LoginResponseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.TokenResponse;
import com.waitomo.sistema_rh.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping
    @Operation(summary = "Realizar login")
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

    @PostMapping("/token/{id}")
    @Operation(summary = "Validar token")
    public ResponseEntity<ResponseMessageStatus> validateToken(@PathVariable Long id, @RequestBody TokenResponse tokenResponse){
        ResponseMessageStatus response = service.verifyToken(id,tokenResponse.token());

        return ResponseEntity.ok(response);
    }
}
