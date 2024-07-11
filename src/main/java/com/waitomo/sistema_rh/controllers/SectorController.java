package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.SectorDTO;
import com.waitomo.sistema_rh.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        URI path = uriBuilder.path("sector/{id}").buildAndExpand(sector.getId()).toUri();

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<SectorDTO>> getAllSector(@PageableDefault(size = 15) Pageable pageable){
        Page<SectorDTO> response = service.getAllSectorService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> getSectorById(@PathVariable Long id){
        SectorDTO response = service.getSectorByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessageStatus> updateSetorById(@PathVariable Long id,@RequestBody SectorDTO sectorDTO){
        ResponseMessageStatus response = service.updateSectorByIdService(id,sectorDTO);

        return ResponseEntity.ok(response);
    }
}
