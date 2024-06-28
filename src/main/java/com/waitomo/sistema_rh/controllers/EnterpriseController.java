package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createEnterprise(@RequestBody EnterpriseDTO enterprise, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus enterpriseDTO=enterpriseService.createEnterpriseService(enterprise);
        URI path = uriBuilder.path("/enterprises/{id}").buildAndExpand(enterprise.id()).toUri();

        return ResponseEntity.created(path).body(enterpriseDTO);
    }
}
