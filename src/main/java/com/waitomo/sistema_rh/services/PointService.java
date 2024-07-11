package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Point;
import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import com.waitomo.sistema_rh.repositories.PointRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

        System.out.println(pointDTO.getEmployeeId());

        pointModel.setEmployeeId(pointDTO.getEmployeeId());
        pointModel.setDate(pointDTO.getDate());
        pointModel.setCheckInTime(LocalTime.parse(formatterLocalTime(pointDTO.getCheckInTime())));
        pointModel.setCheckOutLunch(LocalTime.parse(formatterLocalTime(pointDTO.getCheckOutLunch())));
        pointModel.setBackLunch(LocalTime.parse(formatterLocalTime(pointDTO.getBackLunch())));
        pointModel.setCheckOutTime(LocalTime.parse(formatterLocalTime(pointDTO.getCheckOutTime())));

        repository.save(pointModel);

        String message = "Ponto criado com sucesso!";
        Integer status=201;
        return new ResponseMessageStatus(message,status);
    }

    public Page<PointDTO> getAllPointService(Pageable pageable){
        return repository
                .findAll(pageable)
                .map(point -> modelMapper.map(point, PointDTO.class));
    }

    public PointDTO getPointByIdService(Long id){
        existsEmployee(id);

        Point point = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ponto não encontrado"));
        return modelMapper.map(point, PointDTO.class);
    }

    public String formatterLocalTime(LocalTime localTime){
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public void existsEmployee(Long id){
       boolean existsEmployee = employeeRepository.existsById(id);

       if(!existsEmployee){
           throw new EntityNotFoundException("ID do Funcionário não encontrado");
       }
    }
}
