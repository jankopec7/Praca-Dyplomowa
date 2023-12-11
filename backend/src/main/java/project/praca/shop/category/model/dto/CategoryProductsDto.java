package project.praca.shop.category.model.dto;

import org.springframework.data.domain.Page;
import project.praca.shop.common.model.Category;
import project.praca.shop.common.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}

