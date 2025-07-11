package sd.softdeving.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.softdeving.orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
