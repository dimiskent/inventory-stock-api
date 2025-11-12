package be.skenteridis.inventorystock.repository;

import be.skenteridis.inventorystock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT SUM(p.amount) FROM Product p")
    Double findInventoryValue();
}
