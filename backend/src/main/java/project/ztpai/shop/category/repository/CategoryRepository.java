package project.ztpai.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ztpai.shop.common.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findBySlug(String slug);
}
