package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.SectorDTO;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.models.Sector;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import com.waitomo.sistema_rh.repositories.SectorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorService {
    @Autowired
    private SectorRepository repository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseMessageStatus createSector(SectorDTO sectorDTO){
        final String MESSAGE_SUCCESS="Setor criado com sucesso";
        final Integer STATUS_SUCCESS=200;
        final String MESSAGE_FAILED="Este setor já existe";
        final Integer STATUS_FAILED=409;
        final String MESSAGE_BAD="ID da Empresa não encontrado";
        final Integer STATUS_BAD=404;

        if(!existEnterpriseId(sectorDTO.enterprise_id())){
            return new ResponseMessageStatus(
                    MESSAGE_BAD,
                    STATUS_BAD
            );
        }

        boolean existSectorName=existSector(sectorDTO.name(),sectorDTO.enterprise_id());

        if(existSectorName){
            return new ResponseMessageStatus(
                    MESSAGE_FAILED,
                    STATUS_FAILED
            );
        }else{
            Sector sectorModel = modelMapper.map(sectorDTO, Sector.class);
            sectorModel.setName(sectorDTO.name());
            sectorModel.setEnterprise_id(sectorDTO.enterprise_id());
            repository.save(sectorModel);

            return new ResponseMessageStatus(
                    MESSAGE_SUCCESS,
                    STATUS_SUCCESS
            );
        }
    }

    public boolean existSector(String name,Long enterprise_id){
        Long existSector = repository.existsByNameAndEntepriseId(name,enterprise_id);

        if(existSector != 0 ){
            return true;
        }else{
            return false;
        }
    }

    public boolean existEnterpriseId(Long id){
        return enterpriseRepository.existsById(id);
    }
}
