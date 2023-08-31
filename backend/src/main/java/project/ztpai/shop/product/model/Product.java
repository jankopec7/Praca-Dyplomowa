package project.ztpai.shop.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

    @Entity
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String category;
        private String description;
        private String fullDescription;
        private BigDecimal price;
        private String currency;
        private String image;
        private String slug;
    }
