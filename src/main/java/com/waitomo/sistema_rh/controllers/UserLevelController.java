package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.UserLevelDTO;
import com.waitomo.sistema_rh.models.UserLevel;
import com.waitomo.sistema_rh.services.UserLevelService;
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
@RequestMapping("/user_level")
public class UserLevelController {
    @Autowired
    private UserLevelService service;

    final static String MESSAGE_CREATED = "Nível de usuário criado com sucesso";
    final static Integer STATUS_CREATED = 201;

    @PostMapping
    @Operation(summary = "Criar nível de usuário")
    public ResponseEntity<ResponseMessageStatus> createUserLevel(@RequestBody UserLevelDTO userLevel, UriComponentsBuilder uriBuilder){
        UserLevel userLevelModel = service.createUserLevelService(userLevel);
        Long userLevelId = userLevelModel.getId();
        URI path = uriBuilder.path("user_level/{id}").buildAndExpand(userLevelId).toUri();

        ResponseMessageStatus response = new ResponseMessageStatus(MESSAGE_CREATED,STATUS_CREATED);
        return ResponseEntity.created(path).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos usuários")
    public ResponseEntity<Page<UserLevelDTO>> getAllUserLevel(@PageableDefault(size = 15) Pageable pageable){
        Page<UserLevelDTO> response = service.getAllUserLevelService(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter usuário por id")
    public ResponseEntity<UserLevelDTO> getUserLevelById(@PathVariable Long id){
        UserLevelDTO response = service.getUserLevelByIdService(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário por id")
    public ResponseEntity<ResponseMessageStatus> updateUserLevelById(@PathVariable Long id,@RequestBody UserLevelDTO userLevelDTO){
        ResponseMessageStatus response = service.updateUserLevelByIdService(id,userLevelDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário por id")
    public ResponseEntity<Void> deleteUserLevelById(@PathVariable Long id){
        service.deleteUserLevelByIdService(id);

        return ResponseEntity.noContent().build();
    }
}
