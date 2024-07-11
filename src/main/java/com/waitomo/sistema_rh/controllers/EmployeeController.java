package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createEmployee(@RequestBody EmployeeDTO employee, UriComponentsBuilder uriBuilder){
       ResponseMessageStatus response = service.createEmployeeService(employee);
       URI path = uriBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri();
       return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployee(@PageableDefault(size = 15) Pageable pageable){
        Page<EmployeeDTO> response = service.getAllEmployeeService(pageable);

        return ResponseEntity.ok(response);
    }
}
