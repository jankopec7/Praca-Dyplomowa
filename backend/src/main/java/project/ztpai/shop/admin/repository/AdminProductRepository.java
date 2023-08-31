package project.ztpai.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ztpai.shop.admin.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
