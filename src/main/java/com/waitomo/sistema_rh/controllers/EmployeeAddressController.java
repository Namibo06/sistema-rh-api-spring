package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EmployeeAddressDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.ViacepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class EmployeeAddressController {
    @Autowired
    private ViacepService viacepService;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> verifyAddress(@RequestBody String cep){
        ResponseMessageStatus employeeAddressDTO = viacepService.verifyAddressService(cep);
        return ResponseEntity.ok(employeeAddressDTO);
    }
}
