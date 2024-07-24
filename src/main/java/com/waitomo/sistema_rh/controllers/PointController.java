package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Point;
import com.waitomo.sistema_rh.services.PointService;
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
@RequestMapping("point")
public class PointController {
    @Autowired
    private PointService service;

    final static String MESSAGE_CREATED = "Ponto criado com sucesso!";
    final static Integer STATUS_CREATED = 201;

    @PostMapping
    @Operation(summary = "Criar ponto")
    public ResponseEntity<ResponseMessageStatus> createPoint(@RequestBody PointDTO pointDTO, UriComponentsBuilder uriBuilder){
        Point pointModel = service.createPointService(pointDTO);
        Long pointId = pointModel.getId();
        URI path = uriBuilder.path("/point/{id}").buildAndExpand(pointId).toUri();

        ResponseMessageStatus response = new ResponseMessageStatus(MESSAGE_CREATED,STATUS_CREATED);

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar pontos")
    public ResponseEntity<Page<PointDTO>> getAllPoint(@PageableDefault(size = 15) Pageable pageable){
        Page<PointDTO> response = service.getAllPointService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um ponto por id")
    public ResponseEntity<PointDTO> getPointById(@PathVariable Long id){
        PointDTO response = service.getPointByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ponto por id")
    public ResponseEntity<ResponseMessageStatus> updatePointById(@PathVariable Long id,@RequestBody PointDTO pointDTO){
        ResponseMessageStatus response = service.updatePointByIdService(id,pointDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar ponto por id")
    public ResponseEntity<Void> deletePointById(@PathVariable Long id){
        service.deletPointeById(id);

        return ResponseEntity.noContent().build();
    }
}
