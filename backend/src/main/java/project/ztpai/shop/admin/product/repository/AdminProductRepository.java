package project.ztpai.shop.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ztpai.shop.admin.product.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
