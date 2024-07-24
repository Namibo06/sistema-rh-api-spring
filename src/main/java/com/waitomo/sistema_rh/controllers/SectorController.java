package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.SectorDTO;
import com.waitomo.sistema_rh.models.Sector;
import com.waitomo.sistema_rh.services.SectorService;
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
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorService service;

    final String MESSAGE_CREATED="Setor criado com sucesso";
    final Integer STATUS_CREATED=200;

    @PostMapping
    @Operation(summary = "Criar setor")
    public ResponseEntity<ResponseMessageStatus> createSector(@RequestBody SectorDTO sector, UriComponentsBuilder uriBuilder){
        Sector sectorModel = service.createSectorService(sector);
        Long sectorId = sectorModel.getId();
        URI path = uriBuilder.path("sector/{id}").buildAndExpand(sectorId).toUri();

        ResponseMessageStatus response = new ResponseMessageStatus(MESSAGE_CREATED,STATUS_CREATED);

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar setores")
    public ResponseEntity<Page<SectorDTO>> getAllSector(@PageableDefault(size = 15) Pageable pageable){
        Page<SectorDTO> response = service.getAllSectorService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um setor por id")
    public ResponseEntity<SectorDTO> getSectorById(@PathVariable Long id){
        SectorDTO response = service.getSectorByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizaar setor por id")
    public ResponseEntity<ResponseMessageStatus> updateSetorById(@PathVariable Long id,@RequestBody SectorDTO sectorDTO){
        ResponseMessageStatus response = service.updateSectorByIdService(id,sectorDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar setor por id")
    public ResponseEntity<Void> deleteSectorById(@PathVariable Long id){
        service.deleteSectorByIdService(id);

        return ResponseEntity.noContent().build();
    }
}
