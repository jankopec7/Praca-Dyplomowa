package project.praca.shop.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.praca.shop.common.model.Review;
import project.praca.shop.common.repository.ReviewRepository;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
