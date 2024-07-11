package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.UserLevelDTO;
import com.waitomo.sistema_rh.services.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user_level")
public class UserLevelController {
    @Autowired
    private UserLevelService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createUserLevel(@RequestBody UserLevelDTO userLevel, UriComponentsBuilder uriBuilder){
        ResponseMessageStatus response = service.createUserLevelService(userLevel);
        URI path = uriBuilder.path("user_level/{id}").buildAndExpand(userLevel.getId()).toUri();

        return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<UserLevelDTO>> getAllUserLevel(@PageableDefault(size = 15) Pageable pageable){
        Page<UserLevelDTO> response = service.getAllUserLevelService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLevelDTO> getUserLevelById(@PathVariable Long id){
        UserLevelDTO response = service.getUserLevelByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessageStatus> updateUserLevelById(@PathVariable Long id,@RequestBody UserLevelDTO userLevelDTO){
        ResponseMessageStatus response = service.updateUserLevelByIdService(id,userLevelDTO);

        return ResponseEntity.ok(response);
    }
}
