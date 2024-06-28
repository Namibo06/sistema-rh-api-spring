package com.waitomo.sistema_rh.repositories;

import com.waitomo.sistema_rh.dtos.EmployeeAddressDTO;
import com.waitomo.sistema_rh.models.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress,String> {
    @Query(value = "SELECT CASE WHEN COUNT(e.cep) > 0 THEN true ELSE false END FROM tb_employee_address e WHERE e.cep = :cep",nativeQuery = true)
    Long existsByCep(@Param("cep") String cep);

    EmployeeAddress findAddressByCep(String cep);
}
