package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EnterpriseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Criar empresa")
    public ResponseEntity<ResponseMessageStatus> createEnterprise(@RequestBody EnterpriseDTO enterprise, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus enterpriseDTO=enterpriseService.createEnterpriseService(enterprise);
        URI path = uriBuilder.path("/enterprises/{id}").buildAndExpand(enterprise.getId()).toUri();

        return ResponseEntity.created(path).body(enterpriseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar empresas",security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<EnterpriseDTO>> getAllEnterprises(@PageableDefault(size = 15)Pageable pageable){
        Page<EnterpriseDTO> enterprises=enterpriseService.findAllEnterprises(pageable);

        return ResponseEntity.ok(enterprises);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter uma empresa por id")
    public ResponseEntity<EnterpriseDTO> getEnterpriseById(@PathVariable Long id){
        EnterpriseDTO enterpriseDTO = enterpriseService.findEnterpriseById(id);
        return ResponseEntity.ok(enterpriseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar empresa por id")
    public ResponseEntity<ResponseMessageStatus> updateEnterpriseById(@PathVariable Long id,@RequestBody EnterpriseDTO enterpriseDTO){
        ResponseMessageStatus response = enterpriseService.updateEnterpriseByIdService(id,enterpriseDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar empresa por id")
    public ResponseEntity<Void> deleteEnterpriseById(@PathVariable Long id){
        enterpriseService.deleteEnterpriseByIdService(id);

        return ResponseEntity.noContent().build();
    }
}
