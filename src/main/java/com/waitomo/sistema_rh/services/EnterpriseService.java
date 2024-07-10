package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.*;
import com.waitomo.sistema_rh.models.EmployeeAddress;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.models.Sector;
import com.waitomo.sistema_rh.models.UserLevel;
import com.waitomo.sistema_rh.repositories.EmployeeAddressRepository;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import com.waitomo.sistema_rh.repositories.SectorRepository;
import com.waitomo.sistema_rh.repositories.UserLevelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private UserLevelRepository userLevelRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private EmployeeAddressRepository employeeAddressRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseMessageStatus createEnterpriseService(EnterpriseDTO enterprise){
        Enterprise enterpriseModel = new Enterprise();

        enterpriseModel.setCnpj(enterprise.getCnpj());
        if(enterpriseModel.getCnpj() == null || enterpriseModel.getCnpj().isEmpty()){
            throw  new IllegalArgumentException("O campo de CNPJ não pode ser nulo ou vazio");
        }
        if(enterpriseModel.getCnpj().length() != 14){
            throw new IllegalArgumentException("O campo de CNPJ precisa conter 14 caracteres");
        }

        enterpriseModel.setFantasy_name(enterprise.getFantasy_name());
        enterpriseModel.setCompany_name(enterprise.getCompany_name());
        enterpriseModel.setNumber_employees(enterprise.getNumber_employees());

        Enterprise enterpriseCreated=enterpriseRepository.save(enterpriseModel);

        Long idUserLevelDTO=createUserLevelByEnterprise(enterpriseCreated.getId());
        Long idSectorDTO = createSectorByEnterprise(enterpriseCreated.getId());
        String cepDefault = "44095400";

        EmployeeDTO employeeDTO = new EmployeeDTO(
                null,
                "root",
                null,
                LocalDate.of(2003,10,06),
                "others",
                idSectorDTO,
                cepDefault,
                enterpriseCreated.getCnpj(),
                idUserLevelDTO,
                enterpriseCreated.getCnpj().concat("root"),
                "123",
                null);
        employeeService.createEmployeeService(employeeDTO);

        String message="Empresa criada com sucesso!";
        Integer status = 201;

        return new ResponseMessageStatus(
                message,
                status
        );
    }

    public Page<EnterpriseDTO> findAllEnterprises(Pageable pageable){
        return enterpriseRepository
                .findAll(pageable)
                .map(enterprise ->
                    modelMapper.map(enterprise,EnterpriseDTO.class)
                );
    }

    public EnterpriseDTO findEnterpriseById(Long id){
        existsEnterprise(id);
        Enterprise enterprise=enterpriseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        return modelMapper.map(enterprise, EnterpriseDTO.class);
    }

    public void updateEnterpriseById(){

    }

    //testar
    public void deleteEnterpriseById(Long id){
        boolean existsEnterprise=existsEnterprise(id);
        if(!existsEnterprise){
            throw new RuntimeException("Empresa não existe");
        }
        enterpriseRepository.deleteById(id);
    }

    public boolean existsEnterprise(Long id){
        boolean existEnterprise=enterpriseRepository.existsById(id);

        if(!existEnterprise){
            throw new EntityNotFoundException("ID da empresa não encontrado");
        }
        return true;
    }

    public Long createUserLevelByEnterprise(Long enterpriseId){
        UserLevel userLevel = new UserLevel();
        userLevel.setId(null);
        userLevel.setName("root");
        userLevel.setEnterprise_id(enterpriseId);
        UserLevel userLevelCreated=userLevelRepository.save(userLevel);

        Long id=userLevelCreated.getId();
        if (id == null){
            throw new NullPointerException("ID nulo");
        }
        return id;
    }

    public Long createSectorByEnterprise(Long enterpriseId){
        Sector sector = new Sector();
        sector.setId(null);
        sector.setName("root");
        sector.setEnterprise_id(enterpriseId);
        Sector sectorCreated=sectorRepository.save(sector);

        Long id = sectorCreated.getId();
        if(id == null){
            throw new NullPointerException("ID nulo");
        }
        return id;
    }
}
