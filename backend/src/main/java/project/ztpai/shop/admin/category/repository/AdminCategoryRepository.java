package project.ztpai.shop.admin.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ztpai.shop.admin.category.model.AdminCategory;

public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {
}

