package project.ztpai.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ztpai.shop.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
