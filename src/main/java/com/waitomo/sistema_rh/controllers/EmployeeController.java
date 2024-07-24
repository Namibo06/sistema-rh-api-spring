package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Employee;
import com.waitomo.sistema_rh.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Criar funcionário")
    public ResponseEntity<ResponseMessageStatus> createEmployee(@RequestBody EmployeeDTO employee, UriComponentsBuilder uriBuilder){
        Employee employeeModel = service.createEmployeeService(employee);
        Long employeeId = employeeModel.getId();
        URI path = uriBuilder.path("/employee/{id}").buildAndExpand(employeeId).toUri();

        String message="Funcionário criado com sucesso";
        Integer status=201;
        ResponseMessageStatus response = new ResponseMessageStatus(message,status);

       return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar funcionários")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployee(@PageableDefault(size = 15) Pageable pageable){
        Page<EmployeeDTO> response = service.getAllEmployeeService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um funcionário por id")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        EmployeeDTO response = service.getEmployeeByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário por id")
    public ResponseEntity<ResponseMessageStatus> updateEmployeeById(@PathVariable Long id,@RequestBody EmployeeDTO employeeDTO){
        ResponseMessageStatus response = service.updateEmployeeByIdService(id,employeeDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar funcionário por id")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id){
        service.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();
    }
}
