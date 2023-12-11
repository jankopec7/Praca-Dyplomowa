package project.praca.shop.review.controller.dto;

import org.hibernate.validator.constraints.Length;

public record ReviewDto(@Length(min = 2, max = 30) String authorName, @Length(min = 4, max = 500) String content, Long productId) {
}

