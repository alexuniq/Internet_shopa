package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {


}
