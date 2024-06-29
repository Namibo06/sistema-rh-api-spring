package com.waitomo.sistema_rh.repositories;

import com.waitomo.sistema_rh.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point,Long> {
}
