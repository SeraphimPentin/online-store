package nicstore.service;

import nicstore.models.Product;
import nicstore.models.Rating;
import nicstore.models.User;
import nicstore.repository.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @InjectMocks
    RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    void testFindRatingByUserAndProduct() {
        User user = new User();
        Product product = new Product();
        Rating expectedRating = new Rating();
        when(ratingRepository.findRatingByUserAndProduct(user, product)).thenReturn(expectedRating);
        Rating actual = ratingService.findRatingByUserAndProduct(user, product);
        assertEquals(expectedRating, actual);
        verify(ratingRepository).findRatingByUserAndProduct(user, product);
    }

    @Test
    void testSaveRating() {
        User user = new User();
        Product product = new Product();
        Integer ratingValue = 3;
        ratingService.saveRating(user,product,ratingValue);
        ArgumentCaptor<Rating> ratingArgumentCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepository).save(ratingArgumentCaptor.capture());
        Rating expected = ratingArgumentCaptor.getValue();
        assertEquals(expected.getUser(), user);
        assertEquals(expected.getProduct(), product);
        assertEquals(expected.getValue(), ratingValue);
    }

    @Test
    void testDeleteRating() {
        Rating rating = new Rating();
        ArgumentCaptor<Rating> ratingArgumentCaptor = ArgumentCaptor.forClass(Rating.class);
        doNothing().when(ratingRepository).delete(ratingArgumentCaptor.capture());
        ratingService.deleteRating(rating);
        Rating capturedRating = ratingArgumentCaptor.getValue();
        assertEquals(capturedRating, rating);
        verify(ratingRepository).delete(rating);
    }
}