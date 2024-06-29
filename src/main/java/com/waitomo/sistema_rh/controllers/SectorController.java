package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.SectorDTO;
import com.waitomo.sistema_rh.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sectors")
public class SectorController {
    @Autowired
    private SectorService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createSector(@RequestBody SectorDTO sector){
        ResponseMessageStatus response = service.createSector(sector);
        return ResponseEntity.ok(response);
    }
}
