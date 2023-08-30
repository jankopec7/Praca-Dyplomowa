package project.ztpai.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.ztpai.shop.product.model.Product;
import project.ztpai.shop.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
        private final ProductRepository productRepository;

        public List<Product> getProducts() {
            return productRepository.findAll();
        }
    }

