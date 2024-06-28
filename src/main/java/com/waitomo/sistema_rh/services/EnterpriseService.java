package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseMessageStatus createEnterpriseService(EnterpriseDTO enterprise){
        Enterprise enterpriseModel = new Enterprise();

        enterpriseModel.setCnpj(enterprise.cnpj());
        if(enterpriseModel.getCnpj() == null || enterpriseModel.getCnpj().isEmpty()){
            throw  new IllegalArgumentException("O campo de CNPJ não pode ser nulo ou vazio");
        }
        if(enterpriseModel.getCnpj().length() != 14){
            throw new IllegalArgumentException("O campo de CNPJ precisa conter 14 caracteres");
        }

        enterpriseModel.setFantasy_name(enterprise.fantasy_name());
        enterpriseModel.setCompany_name(enterprise.company_name());
        enterpriseModel.setNumber_employees(enterprise.number_employees());

        enterpriseRepository.save(enterpriseModel);

        String message="Empresa criada com sucesso!";
        Integer status = 201;
        ResponseMessageStatus responseMessageStatus = new ResponseMessageStatus(
                message,
                status
        );

        return responseMessageStatus;
    }
}
