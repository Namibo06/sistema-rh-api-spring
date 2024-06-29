package com.waitomo.sistema_rh.repositories;

import com.waitomo.sistema_rh.models.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLevelRepository extends JpaRepository<UserLevel,Long> {

}
