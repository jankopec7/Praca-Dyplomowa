package project.ztpai.shop.category.model.dto;

import org.springframework.data.domain.Page;
import project.ztpai.shop.common.model.Category;
import project.ztpai.shop.common.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}

