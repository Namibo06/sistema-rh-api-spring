package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EnterpriseDTO createEnterpriseService(EnterpriseDTO enterprise){
        Enterprise enterpriseModel = modelMapper.map(enterprise,Enterprise.class);
        enterpriseModel.setCnpj(enterpriseModel.getCnpj());
        enterpriseModel.setFantasy_name(enterpriseModel.getFantasy_name());
        enterpriseModel.setCompany_name(enterpriseModel.getCompany_name());
        enterpriseModel.setNumber_employees(enterpriseModel.getNumber_employees());
        enterpriseRepository.save(enterpriseModel);

        return modelMapper.map(enterpriseModel,EnterpriseDTO.class);
    }
}
