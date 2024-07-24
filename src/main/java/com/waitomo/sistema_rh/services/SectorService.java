package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.dtos.SectorDTO;
import com.waitomo.sistema_rh.exceptions.AlreadyExistsException;
import com.waitomo.sistema_rh.exceptions.NotFoundException;
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

    public Sector createSectorService(SectorDTO sectorDTO){
        if(!existEnterpriseId(sectorDTO.getEnterprise_id())){
            throw new NotFoundException("ID da Empresa",'o');
        }

        boolean existSectorName=existSector(sectorDTO.getName(),sectorDTO.getEnterprise_id());

        if(existSectorName){
            throw new AlreadyExistsException('e',"setor");

        }else{
            Sector sectorModel = modelMapper.map(sectorDTO, Sector.class);
            sectorModel.setName(sectorDTO.getName());
            sectorModel.setEnterprise_id(sectorDTO.getEnterprise_id());
            repository.save(sectorModel);

            return sectorModel;
        }
    }

    public Page<SectorDTO> getAllSectorService(Pageable pageable){
        Page<Sector> sectorPage = repository.findAll(pageable);

        if(sectorPage.isEmpty()){
            throw new NotFoundException("Setores",'o');
        }

        return sectorPage
                .map(sector -> modelMapper.map(sector, SectorDTO.class));
    }

    public SectorDTO getSectorByIdService(Long id){
        existsSectorById(id);

        Sector sector = repository.findById(id).orElseThrow(() -> new NotFoundException("Setor",'o'));
        return modelMapper.map(sector, SectorDTO.class);
    }

    public ResponseMessageStatus updateSectorByIdService(Long id,SectorDTO sectorDTO){
        existsSectorById(id);

        Sector sector = repository.findById(id).orElseThrow(() -> new NotFoundException("Setor",'o'));
        sector.setName(sectorDTO.getName());
        sector.setEnterprise_id(sectorDTO.getEnterprise_id());
        repository.save(sector);

        String message = "Setor atualizado com sucesso";
        Integer status = 200;
        return new ResponseMessageStatus(message,status);
    }

    public void deleteSectorByIdService(Long id){
        existsSectorById(id);

        repository.deleteById(id);
    }

    public boolean existSector(String name,Long enterprise_id){
        Long existSector = repository.existsByNameAndEntepriseId(name,enterprise_id);

        return existSector != 0;
    }

    public void existsSectorById(Long id){
        boolean existsSector = repository.existsById(id);

        if(!existsSector){
            throw new NotFoundException("Setor",'o');
        }
    }

    public boolean existEnterpriseId(Long id){
        return enterpriseRepository.existsById(id);
    }
}
