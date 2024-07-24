package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.PointDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.exceptions.NotFoundException;
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

    public Point createPointService(PointDTO pointDTO){
        Point pointModel = modelMapper.map(pointDTO, Point.class);

        pointModel.setEmployeeId(pointDTO.getEmployeeId());
        pointModel.setDate(pointDTO.getDate());
        pointModel.setCheckInTime(pointDTO.getCheckInTime());
        pointModel.setCheckOutLunch(pointDTO.getCheckOutLunch());
        pointModel.setBackLunch(pointDTO.getBackLunch());
        pointModel.setCheckOutTime(pointDTO.getCheckOutTime());

        repository.save(pointModel);

        return pointModel;
    }

    public Page<PointDTO> getAllPointService(Pageable pageable){
        Page<Point> pointPage = repository.findAll(pageable);

        if(pointPage.isEmpty()){
            throw new NotFoundException("Pontos",'o');
        }

        return pointPage
                .map(point -> modelMapper.map(point, PointDTO.class));
    }

    public PointDTO getPointByIdService(Long id){
        existsPoint(id);

        Point point = repository.findById(id).orElseThrow(() -> new NotFoundException("Ponto",'o'));
        return modelMapper.map(point, PointDTO.class);
    }

    public ResponseMessageStatus updatePointByIdService(Long id,PointDTO pointDTO){
        existsPoint(id);

        Point point = repository.findById(id).orElseThrow(() -> new NotFoundException("Ponto",'o'));
        point.setEmployeeId(pointDTO.getEmployeeId());
        point.setDate(pointDTO.getDate());
        point.setCheckInTime(pointDTO.getCheckInTime());
        point.setBackLunch(pointDTO.getBackLunch());
        point.setCheckOutLunch(pointDTO.getCheckOutLunch());
        point.setCheckOutTime(pointDTO.getCheckOutTime());
        repository.save(point);

        String message = "Ponto atualizado com sucesso";
        Integer status = 200;
        return new ResponseMessageStatus(message,status);
    }

    public void deletPointeById(Long id){
        existsPoint(id);

        repository.deleteById(id);
    }

    public void existsPoint(Long id){
        boolean existsPoint = repository.existsById(id);

        if(!existsPoint){
            throw new NotFoundException("Ponto",'o');
        }
    }

    public void existsEmployee(Long id){
       boolean existsEmployee = employeeRepository.existsById(id);

       if(!existsEmployee){
           throw new NotFoundException("ID do Funcionário",'o');
       }
    }
}
