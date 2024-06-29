package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
       URI path = uriBuilder.path("/employee/{id}").buildAndExpand(employee.id()).toUri();
       return ResponseEntity.created(path).body(response);
    }
}
