package project.praca.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.praca.shop.product.service.dto.ProductDto;
import project.praca.shop.common.model.Product;
import project.praca.shop.common.model.Review;
import project.praca.shop.common.repository.ProductRepository;
import project.praca.shop.common.repository.ReviewRepository;
import project.praca.shop.product.service.dto.ReviewDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductBySlug(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(), true);
        return mapToProductDto(product, reviews);
    }

    private ProductDto mapToProductDto(Product product, List<Review> reviews) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .reviews(reviews.stream().map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .productId(review.getProductId())
                        .authorName(review.getAuthorName())
                        .content(review.getContent())
                        .moderate(review.isModerated())
                        .build()
                ).toList())
                .build();
    }
}



