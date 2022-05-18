package cz.cvut.fel.ts1.sp.repository;

import cz.cvut.fel.ts1.sp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}