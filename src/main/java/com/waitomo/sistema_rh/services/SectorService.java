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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if(!existEnterpriseId(sectorDTO.getEnterprise_id())){
            return new ResponseMessageStatus(
                    MESSAGE_BAD,
                    STATUS_BAD
            );
        }

        boolean existSectorName=existSector(sectorDTO.getName(),sectorDTO.getEnterprise_id());

        if(existSectorName){
            return new ResponseMessageStatus(
                    MESSAGE_FAILED,
                    STATUS_FAILED
            );
        }else{
            Sector sectorModel = modelMapper.map(sectorDTO, Sector.class);
            sectorModel.setName(sectorDTO.getName());
            sectorModel.setEnterprise_id(sectorDTO.getEnterprise_id());
            repository.save(sectorModel);

            return new ResponseMessageStatus(
                    MESSAGE_SUCCESS,
                    STATUS_SUCCESS
            );
        }
    }

    public Page<SectorDTO> getAllSectorService(Pageable pageable){
        return repository
                .findAll(pageable)
                .map(sector -> modelMapper.map(sector, SectorDTO.class));
    }

    public SectorDTO getSectorByIdService(Long id){
        existsSectorById(id);

        Sector sector = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
        return modelMapper.map(sector, SectorDTO.class);
    }

    public ResponseMessageStatus updateSectorByIdService(Long id,SectorDTO sectorDTO){
        existsSectorById(id);

        Sector sector = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Setor não encontrado"));
        sector.setName(sectorDTO.getName());
        sector.setEnterprise_id(sectorDTO.getEnterprise_id());
        repository.save(sector);

        String message = "Setor atualizado com sucesso";
        Integer status = 200;
        return new ResponseMessageStatus(message,status);
    }

    public boolean existSector(String name,Long enterprise_id){
        Long existSector = repository.existsByNameAndEntepriseId(name,enterprise_id);

        return existSector != 0;
    }

    public void existsSectorById(Long id){
        boolean existsSector = repository.existsById(id);

        if(!existsSector){
            throw new EntityNotFoundException("Setor não encontrado");
        }
    }

    public boolean existEnterpriseId(Long id){
        return enterpriseRepository.existsById(id);
    }
}
