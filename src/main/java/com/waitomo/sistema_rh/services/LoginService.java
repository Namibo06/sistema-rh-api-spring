package com.waitomo.sistema_rh.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.waitomo.sistema_rh.dtos.EmployeeDTO;
import com.waitomo.sistema_rh.dtos.LoginResponseDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.models.Employee;
import com.waitomo.sistema_rh.models.Sector;
import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import com.waitomo.sistema_rh.repositories.SectorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    public String createToken(){
        try{
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + 3600 * 1000 * 3);
            Algorithm algorithm = Algorithm.HMAC256("WaitomoHiper12çCorporation");

            System.out.println(JWT.create()
                    .withIssuer("Sistema RH")
                    .withExpiresAt(expirationDate)
                    .sign(algorithm));

            return JWT.create()
                    .withIssuer("Sistema RH")
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Falha ao criar token: "+e);
        }
    }

    public void verifyToken(){

    }

    public ResponseMessageStatus updateTokenByUser(String login, String token){
        Optional<Employee> employee = repository.findByLogin(login);
        if (employee.isEmpty()) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        Employee employeeModel = employee.get();

        if(token.isEmpty()){
            throw new IllegalArgumentException("Token nulo ou vazio");
        }

        employeeModel.setToken(token);
        repository.save(employeeModel);

        String message = "Token criado e atualizado com sucesso";
        Integer status = 200;

        return new ResponseMessageStatus(message,status);
    }

    public EmployeeDTO findByLogin(String login){
        Employee employee= repository.findByLogin(login).orElseThrow(()-> new EntityNotFoundException("Funcionário não encontrado"));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public boolean findEmployeeByLoginAndPassword(LoginResponseDTO credentials){
        Optional<Employee> optionalEmployee = repository.findByLogin(credentials.login());

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            boolean checkPassword = encoder.matches(credentials.password(), employee.getPassword());
            if (!checkPassword) {
                throw new BadCredentialsException("Senhas não batem");
            }
            return true;
        }
        return false;
    }

   /* public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails employee = repository.findByLogin(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o login: " + username);
        }
        return employee;
    }*/

}
