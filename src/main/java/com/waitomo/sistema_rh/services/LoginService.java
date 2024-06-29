package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public void createToken(){

    }

    public void verifyToken(){

    }

    public void updateTokenByUser(){

    }
}
