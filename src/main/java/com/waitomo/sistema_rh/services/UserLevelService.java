package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.UserLevelDTO;
import com.waitomo.sistema_rh.models.UserLevel;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import com.waitomo.sistema_rh.repositories.UserLevelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLevelService {
    @Autowired
    private UserLevelRepository repository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public ResponseMessageStatus createUserLevelService(UserLevelDTO userLevel){
        UserLevel userLevelModel = new UserLevel();
        userLevelModel.setName(userLevel.name());
        if(!existEnterpriseId(userLevel.enterprise_id())){
            throw new EntityNotFoundException("ID da empresa não encontrado");
        }

        userLevelModel.setEnterprise_id(userLevelModel.getEnterprise_id());
        repository.save(userLevelModel);

        final String message = "Nível de usuário criado com sucesso";
        final Integer status = 201;

        return new ResponseMessageStatus(message,status);
    }

    public boolean existEnterpriseId(Long id){
        return enterpriseRepository.existsById(id);
    }
}
