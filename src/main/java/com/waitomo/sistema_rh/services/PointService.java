package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Point;
import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import com.waitomo.sistema_rh.repositories.PointRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    private PointRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseMessageStatus createPointService(PointDTO pointDTO){
        Point pointModel = modelMapper.map(pointDTO, Point.class);
        if(!existsEmployee(pointDTO.employeeId())){
            throw new EntityNotFoundException("ID do Funcionário não encontrado");
        }
        pointModel.setEmployeeId(pointDTO.employeeId());
        pointModel.setDate(pointDTO.date());
        pointModel.setChekInTime(pointDTO.chekInTime());
        pointModel.setCheckOutLunch(pointDTO.checkOutLunch());
        pointModel.setBackLunch(pointDTO.backLunch());
        pointModel.setCheckOutTime(pointDTO.CheckOutTime());

        repository.save(pointModel);

        String message = "Ponto criado com sucesso!";
        Integer status=201;
        return new ResponseMessageStatus(message,status);
    }

    public boolean existsEmployee(Long id){
        return employeeRepository.existsById(id);
    }
}
