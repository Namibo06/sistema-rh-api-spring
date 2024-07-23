package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EmployeeAddressDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.ViacepService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/address")
public class EmployeeAddressController {
    @Autowired
    private ViacepService viacepService;

    @PostMapping
    @Operation(summary = "Verificar endereço")
    public ResponseEntity<ResponseMessageStatus> verifyAddress(@RequestBody String cep, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus employeeAddressDTO = viacepService.verifyAddressService(cep);
        URI path = uriBuilder.path("address/{cep}").buildAndExpand(cep).toUri();
        return ResponseEntity.created(path).body(employeeAddressDTO);
    }
}
