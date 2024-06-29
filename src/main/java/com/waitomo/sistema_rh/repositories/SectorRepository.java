package com.waitomo.sistema_rh.repositories;

import com.waitomo.sistema_rh.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector,Long> {
    @Query(value = "SELECT CASE WHEN COUNT(s.name) > 0 THEN true ELSE false END FROM tb_sector as s WHERE s.name = :name AND s.enterprise_id = :enterprise_id",nativeQuery = true)
    Long existsByNameAndEntepriseId(String name,Long enterprise_id);
}
