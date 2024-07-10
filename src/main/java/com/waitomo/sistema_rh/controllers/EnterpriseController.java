package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createEnterprise(@RequestBody EnterpriseDTO enterprise, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus enterpriseDTO=enterpriseService.createEnterpriseService(enterprise);
        URI path = uriBuilder.path("/enterprises/{id}").buildAndExpand(enterprise.getId()).toUri();

        return ResponseEntity.created(path).body(enterpriseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<EnterpriseDTO>> enterprises(@PageableDefault(size = 15)Pageable pageable){
        Page<EnterpriseDTO> enterprises=enterpriseService.findAllEnterprises(pageable);

        return ResponseEntity.ok(enterprises);
    }

    //implementar metodo do README
    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseDTO> enterpriseById(@PathVariable Long id){
        EnterpriseDTO enterpriseDTO = enterpriseService.findEnterpriseById(id);
        return ResponseEntity.ok(enterpriseDTO);
    }
}
