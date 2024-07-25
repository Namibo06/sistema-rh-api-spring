package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.LoginResponseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.TokenRequestDTO;
import com.waitomo.sistema_rh.dtos.TokenResponseDTO;
import com.waitomo.sistema_rh.exceptions.NotFoundException;
import com.waitomo.sistema_rh.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginResponseDTO credentials){
        try {
            boolean existsEmployee=service.findEmployeeByLoginAndPassword(credentials);

            if(!existsEmployee){
                throw new NotFoundException("Funcionário",'o');
            }

            TokenResponseDTO tokenCreated = service.createToken();

            service.updateTokenByUser(credentials.login(),tokenCreated);
            return ResponseEntity.ok(tokenCreated);
        }catch (Exception e){
            String message=e.getMessage();
            Integer status = 400;

            TokenResponseDTO response = new TokenResponseDTO("",message,status);
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/token/{id}")
    @Operation(summary = "Validar token",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ResponseMessageStatus> validateToken(@PathVariable Long id, @RequestBody TokenRequestDTO tokenResponse){
        ResponseMessageStatus response = service.verifyToken(id,tokenResponse.token());

        return ResponseEntity.ok(response);
    }
}
