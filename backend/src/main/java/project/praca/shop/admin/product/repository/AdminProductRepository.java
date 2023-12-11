package project.praca.shop.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.praca.shop.admin.product.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
