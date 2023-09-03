package project.ztpai.shop.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.ztpai.shop.common.model.Review;
import project.ztpai.shop.common.repository.ReviewRepository;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
