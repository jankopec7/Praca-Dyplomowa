package project.praca.shop.homepage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.praca.shop.common.model.Product;
import project.praca.shop.common.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePageService {
    private final ProductRepository productRepository;


    public List<Product> getSaleProducts(){
       return productRepository.findBySalePriceIsNotNull();

    }
}
