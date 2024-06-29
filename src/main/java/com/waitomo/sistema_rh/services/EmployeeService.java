package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Employee;
import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseMessageStatus createEmployeeService(EmployeeDTO employeeDTO){
        Employee employeeModel = modelMapper.map(employeeDTO, Employee.class);
        employeeModel.setFirstName(employeeDTO.firstName());
        employeeModel.setLastName(employeeDTO.lastName());
        employeeModel.setDateNasciment(employeeDTO.dateNasciment());
        employeeModel.setGender(employeeDTO.gender());
        employeeModel.setSector(employeeDTO.sector());
        employeeModel.setCep(employeeDTO.cep());
        employeeModel.setCnpjEnterprise(employeeDTO.cnpjEnterprise());
        employeeModel.setUserLevel(employeeDTO.userLevel());
        employeeModel.setLogin(employeeDTO.login());
        employeeModel.setPassword(employeeDTO.password());
        employeeModel.setToken(employeeDTO.token());

        repository.save(employeeModel);

        String message="Funcionário criado com sucesso";
        Integer status=201;
        return new ResponseMessageStatus(message,status);
    }
}
