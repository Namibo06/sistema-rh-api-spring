package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<LoginResponseDTO> authentication(@RequestBody LoginResponseDTO credentials){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.login(),credentials.password());
        Authentication authentication = authenticationManager.authenticate(token);

        //pegar o serviço pra criar token

        //pegar serviço pra atualizar token ao usuário
    }
}
