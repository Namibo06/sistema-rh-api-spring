package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.SectorDTO;
import com.waitomo.sistema_rh.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createSector(@RequestBody SectorDTO sector, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus response = service.createSector(sector);
        URI path = uriBuilder.path("sector/{id}").buildAndExpand(sector.id()).toUri();

        return ResponseEntity.created(path).body(response);
    }
}
