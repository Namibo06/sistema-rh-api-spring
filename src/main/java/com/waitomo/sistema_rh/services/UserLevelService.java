package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.UserLevelDTO;
import com.waitomo.sistema_rh.models.UserLevel;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import com.waitomo.sistema_rh.repositories.UserLevelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLevelService {
    @Autowired
    private UserLevelRepository repository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseMessageStatus createUserLevelService(UserLevelDTO userLevel){
        UserLevel userLevelModel = modelMapper.map(userLevel, UserLevel.class);
        userLevelModel.setName(userLevel.getName());
        if(!existEnterpriseId(userLevel.getEnterprise_id())){
            throw new EntityNotFoundException("ID da empresa não encontrado");
        }

        userLevelModel.setEnterprise_id(userLevel.getEnterprise_id());
        repository.save(userLevelModel);

        String message = "Nível de usuário criado com sucesso";
        Integer status = 201;

        return new ResponseMessageStatus(message,status);
    }

    public Page<UserLevelDTO> getAllUserLevelService(Pageable pageable){
        return repository
                .findAll(pageable)
                .map(userLevel -> modelMapper.map(userLevel, UserLevelDTO.class));
    }

    public UserLevelDTO getUserLevelByIdService(Long id){
        existsUserLevel(id);

        Optional<UserLevel> userLevel = repository.findById(id);
        return modelMapper.map(userLevel, UserLevelDTO.class);
    }

    public void existsUserLevel(Long id){
        boolean existsUserLevelById = repository.existsById(id);

        if(!existsUserLevelById){
            throw new EntityNotFoundException("Nível de Usuário não encontrado");
        }
    }

    public boolean existEnterpriseId(Long id){
        return enterpriseRepository.existsById(id);
    }
}
