package com.waitomo.sistema_rh.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.waitomo.sistema_rh.dtos.*;
import com.waitomo.sistema_rh.exceptions.BadRequestException;
import com.waitomo.sistema_rh.exceptions.NotFoundException;
import com.waitomo.sistema_rh.exceptions.TokenNullOrEmptyException;
import com.waitomo.sistema_rh.models.Employee;
import com.waitomo.sistema_rh.repositories.EmployeeRepository;
import com.waitomo.sistema_rh.repositories.SectorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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

    public TokenResponseDTO createToken(){
        try{
            Date now = new Date();
            Date expirationDate = new Date(now.getTime() + 3600 * 1000 * 3);
            Algorithm algorithm = Algorithm.HMAC256("WaitomoHiper12çCorporation");

            String token = JWT.create()
                    .withIssuer("Sistema RH")
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);

            String message = "Login realizado e token criado";
            Integer status = 201;

            return new TokenResponseDTO(token,message,status);

        }catch (JWTCreationException e){
            throw new RuntimeException("Falha ao criar token: "+e);
        }
    }

    public ResponseMessageStatus verifyToken(Long id,String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("WaitomoHiper12çCorporation");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Sistema RH")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            String message = "Token validado com sucesso";
            Integer status = 200;
            return new ResponseMessageStatus(message,status);
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Falha ao validar token: ",e);
        }catch (Exception e){
            throw new RuntimeException("Hove uma falha: ",e);
        }
    }

    public ResponseMessageStatus updateTokenByUser(String login, TokenResponseDTO token){
        Optional<Employee> employee = repository.findByLogin(login);
        if (employee.isEmpty()) {
            throw new NotFoundException("Funcionário",'o');
        }

        Employee employeeModel = employee.get();

        if(token.getToken().isEmpty()){
            throw new TokenNullOrEmptyException();
        }

        employeeModel.setToken(token.getToken());
        repository.save(employeeModel);

        String message = "Token criado e atualizado com sucesso";
        Integer status = 200;

        return new ResponseMessageStatus(message,status);
    }

    public EmployeeDTO findByLogin(String login){
        Employee employee= repository.findByLogin(login).orElseThrow(()-> new NotFoundException("Funcionário",'o'));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public boolean findEmployeeByLoginAndPassword(LoginResponseDTO credentials){
        Optional<Employee> optionalEmployee = repository.findByLogin(credentials.login());

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            boolean checkPassword = encoder.matches(credentials.password(), employee.getPassword());
            if (!checkPassword) {
                throw new BadRequestException("Senhas não batem");
            }
            return true;
        }
        return false;
    }
}
