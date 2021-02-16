package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}