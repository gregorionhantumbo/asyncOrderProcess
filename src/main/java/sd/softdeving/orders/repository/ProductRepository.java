package sd.softdeving.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.softdeving.orders.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
