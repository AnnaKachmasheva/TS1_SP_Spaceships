package cz.cvut.fel.ts1.sp.repository;

import cz.cvut.fel.ts1.sp.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
