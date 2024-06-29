package com.waitomo.sistema_rh.controllers;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.UserLevelDTO;
import com.waitomo.sistema_rh.services.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_level")
public class UserLevelController {
    @Autowired
    private UserLevelService service;

    @PostMapping
    public ResponseEntity<ResponseMessageStatus> createUserLevel(@RequestBody UserLevelDTO userLevel){
        ResponseMessageStatus response = service.createUserLevelService(userLevel);

        return ResponseEntity.ok(response);
    }
}
