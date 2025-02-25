package com.waitomo.sistema_rh.repositories;

import com.waitomo.sistema_rh.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByCnpj(String cnpj);

    boolean existsById(Long id);
}
