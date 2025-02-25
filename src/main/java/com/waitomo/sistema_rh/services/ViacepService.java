package com.waitomo.sistema_rh.services;

import com.waitomo.sistema_rh.dtos.EmployeeAddressDTO;
import com.waitomo.sistema_rh.dtos.ResponseMessageStatus;
import com.waitomo.sistema_rh.exceptions.BadRequestException;
import com.waitomo.sistema_rh.models.EmployeeAddress;
import com.waitomo.sistema_rh.repositories.EmployeeAddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ViacepService {
    @Autowired
    private EmployeeAddressRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseMessageStatus verifyAddressService(String cep) {
        boolean existsAddress = existAddress(cep);

        if (existsAddress) {
            String MESSAGE_SUCCESS_OK = "Cep localizado e inserido com sucesso!";
            Integer STATUS_SUCCESS_OK = 200;
            return new ResponseMessageStatus(MESSAGE_SUCCESS_OK, STATUS_SUCCESS_OK);

        } else {
            cep = cep.replaceAll("[^0-9]", "");
            String url = "https://viacep.com.br/ws/"+cep+"/json";

            try {
                ResponseEntity<Map<String, String>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});

                if (response.getStatusCode() == HttpStatus.OK) {
                    Map<String, String> responseBody = response.getBody();
                    if (responseBody != null && !responseBody.isEmpty()) {
                        EmployeeAddress address = new EmployeeAddress();
                        address.setCep(cep);
                        address.setUf(responseBody.get("uf"));
                        address.setCity(responseBody.get("localidade"));
                        address.setNeighborhood(responseBody.get("bairro"));
                        address.setRoad(responseBody.get("logradouro"));
                        repository.save(address);

                        String MESSAGE_SUCCESS_CREATED = "Cep criado e inserido com sucesso!";
                        Integer STATUS_SUCCESS_CREATED = 201;
                        return new ResponseMessageStatus(MESSAGE_SUCCESS_CREATED, STATUS_SUCCESS_CREATED);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao consultar serviço ViaCEP: " + e.getMessage());
            }
            throw new BadRequestException("Houve alguma falha ao tentar consultar o serviço da ViaCep");
        }
    }

    public boolean existAddress(String cep){
        cep = cep.replaceAll("[^0-9]","");
        Long result = repository.existsByCep(cep);

        return result != 0;
    }
}
