package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.PointService;
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

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createPoint(@RequestBody PointDTO pointDTO, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus response = service.createPointService(pointDTO);
        URI path = uriBuilder.path("/point/{id}").buildAndExpand(pointDTO.getId()).toUri();

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PointDTO>> getAllPoint(@PageableDefault(size = 15) Pageable pageable){
        Page<PointDTO> response = service.getAllPointService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointDTO> getPointById(@PathVariable Long id){
        PointDTO response = service.getPointByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessageStatus> updatePointById(@PathVariable Long id,@RequestBody PointDTO pointDTO){
        ResponseMessageStatus response = service.updatePointByIdService(id,pointDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointById(@PathVariable Long id){
        service.deletPointeById(id);

        return ResponseEntity.noContent().build();
    }
}
