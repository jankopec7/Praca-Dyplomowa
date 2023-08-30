package project.ztpai.shop.product.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    private String currency;
}
