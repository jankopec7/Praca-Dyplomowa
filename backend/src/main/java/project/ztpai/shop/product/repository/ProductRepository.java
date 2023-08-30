package project.ztpai.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ztpai.shop.product.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

