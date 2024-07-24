package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.UserLevelDTO;
import com.waitomo.sistema_rh.exceptions.NotFoundException;
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

    public UserLevel createUserLevelService(UserLevelDTO userLevel){
        UserLevel userLevelModel = modelMapper.map(userLevel, UserLevel.class);
        userLevelModel.setName(userLevel.getName());

        if(!existEnterpriseId(userLevel.getEnterprise_id())){
            throw new NotFoundException("ID da empresa",'o');
        }

        userLevelModel.setEnterprise_id(userLevel.getEnterprise_id());
        repository.save(userLevelModel);

        return userLevelModel;
    }

    public Page<UserLevelDTO> getAllUserLevelService(Pageable pageable){
        Page<UserLevel> userLevelPage = repository.findAll(pageable);

        if(userLevelPage.isEmpty()){
            throw new NotFoundException("Níveis de usuário",'o');
        }

        return userLevelPage
                .map(userLevel -> modelMapper.map(userLevel, UserLevelDTO.class));
    }

    public UserLevelDTO getUserLevelByIdService(Long id){
        existsUserLevel(id);

        UserLevel userLevel = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nível de Usuário não encontrado"));
        return modelMapper.map(userLevel, UserLevelDTO.class);
    }

    public ResponseMessageStatus updateUserLevelByIdService(Long id,UserLevelDTO userLevelDTO){
        existsUserLevel(id);

        UserLevel userLevel = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nível de Usuário não encontrado"));
        userLevel.setName(userLevelDTO.getName());
        userLevel.setEnterprise_id(userLevelDTO.getEnterprise_id());
        repository.save(userLevel);

        String message = "Nível de usuário atualizado com sucesso";
        Integer status = 200;
        return new ResponseMessageStatus(message,status);
    }

    public void deleteUserLevelByIdService(Long id){
        existsUserLevel(id);

        repository.deleteById(id);
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
