package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createPoint(@RequestBody PointDTO pointDTO, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus response = service.createPointService(pointDTO);
        URI path = uriBuilder.path("/point/{id}").buildAndExpand(pointDTO.id()).toUri();

        return ResponseEntity.created(path).body(response);
    }
}
