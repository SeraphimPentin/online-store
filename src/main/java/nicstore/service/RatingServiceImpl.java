package nicstore.service;

import lombok.RequiredArgsConstructor;
import nicstore.models.Product;
import nicstore.models.Rating;
import nicstore.models.User;
import nicstore.repository.RatingRepository;
import nicstore.service.interfaces.RatingService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;


    public List<Rating> findRatingsByProduct(Product product) {
        return ratingRepository.findRatingsByProduct(product);
    }

    public Rating findRatingByUserAndProduct(User user, Product product) {
        return ratingRepository.findRatingByUserAndProduct(user, product);
    }

    public Integer findRatingsNumberByProduct(Product product) {
        return ratingRepository.countRatingsByProduct(product);
    }

    public Double findAverageRatingByProduct(Product product) {
        return ratingRepository.calculateAverageRatingByProductId(product.getId());
    }

    @Transactional
    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

    @Transactional
    public void updateRatingValueById(Rating rating, Integer ratingValue) {
        ratingRepository.updateRatingValueById(rating.getId(), ratingValue);
    }

    @Transactional
    public void saveRating(User user, Product product, Integer ratingValue) {
        ratingRepository.save(
                Rating.builder()
                        .user(user)
                        .product(product)
                        .value(ratingValue)
                        .build()
        );
    }
}
