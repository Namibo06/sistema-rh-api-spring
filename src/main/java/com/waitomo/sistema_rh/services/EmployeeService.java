package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.EnterpriseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.exceptions.NotFoundException;
import com.waitomo.sistema_rh.models.Employee;
import com.waitomo.sistema_rh.models.Enterprise;
import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import com.waitomo.sistema_rh.repositories.EnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    public Employee createEmployeeService(EmployeeDTO employeeDTO){
        Employee employeeModel = modelMapper.map(employeeDTO, Employee.class);

        employeeModel.setFirstName(employeeDTO.getFirstName());
        employeeModel.setLastName(employeeDTO.getLastName());
        employeeModel.setDateNasciment(employeeDTO.getDateNasciment());
        employeeModel.setGender(employeeDTO.getGender());
        employeeModel.setSector(employeeDTO.getSector());
        employeeModel.setCep(employeeDTO.getCep());
        employeeModel.setCnpjEnterprise(employeeDTO.getCnpjEnterprise());
        employeeModel.setUserLevel(employeeDTO.getUserLevel());
        employeeModel.setLogin(employeeDTO.getLogin());
        employeeModel.setPassword(encoder.encode(employeeDTO.getPassword()));
        employeeModel.setToken(employeeDTO.getToken());
        repository.save(employeeModel);

        updateNumberEmployee(employeeDTO.getCnpjEnterprise());

        return employeeModel;
    }

    public Page<EmployeeDTO> getAllEmployeeService(Pageable pageable){
        Page<Employee> employeeDTOList = repository.findAll(pageable);

        if(employeeDTOList.isEmpty()){
            throw new NotFoundException("Funcionários",'o');
        }

        return employeeDTOList
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }

    public EmployeeDTO getEmployeeByIdService(Long id){
        existsEmployee(id);

        Employee employee = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário",'o'));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public ResponseMessageStatus updateEmployeeByIdService(Long id,EmployeeDTO employeeDTO){
        existsEmployee(id);

        Employee employee = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário",'o'));
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateNasciment(employeeDTO.getDateNasciment());
        employee.setGender(employeeDTO.getGender());
        employee.setSector(employeeDTO.getSector());
        employee.setCep(employeeDTO.getCep());
        employee.setCnpjEnterprise(employeeDTO.getCnpjEnterprise());
        employee.setUserLevel(employeeDTO.getUserLevel());
        employee.setLogin(employeeDTO.getLogin());
        employee.setPassword(encoder.encode(employeeDTO.getPassword()));
        employee.setToken(employeeDTO.getToken());
        repository.save(employee);

        String message = "Funcionário atualizado com sucesso";
        Integer status = 200;
        return new ResponseMessageStatus(message,status);
    }

    public void deleteEmployeeById(Long id){
        existsEmployee(id);

        repository.deleteById(id);
    }

    public void existsEmployee(Long id){
        boolean existsEmployeeById = repository.existsById(id);

        if(!existsEmployeeById){
            throw new NotFoundException("Funcionário",'o');
        }
    }

    public void updateNumberEmployee(String cnpj){
        Enterprise enterprise= enterpriseRepository.findByCnpj(cnpj);
        if(enterprise.getNumber_employees() == null){
            enterprise.setNumber_employees(0);
        }
        enterprise.setNumber_employees(enterprise.getNumber_employees() + 1);
        enterpriseRepository.save(enterprise);
    }
}
