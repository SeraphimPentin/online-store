package nicstore.controllers;

import lombok.RequiredArgsConstructor;
import nicstore.dto.product.CommentRequest;
import nicstore.dto.product.ProductCharacteristicsResponse;
import nicstore.dto.product.ReviewResponse;
import nicstore.service.CartService;
import nicstore.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/nic-shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final CartService cartService;


    @PutMapping
    public ResponseEntity<Void> addProductToCart(Long productId) {
        cartService.addProductToCart(productId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> changeProductQuantityInCart(Long productId, @RequestParam(name = "op") String operation) {
        cartService.changeQuantityProductInCart(productId, operation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductCharacteristicsResponse> getProductPage(@PathVariable Long productId) {
        return ResponseEntity.ok(shopService.getProductPage(productId));
    }

    @GetMapping("/{productId}/edit-review") // получить содержимое отзыва для редактирования
    public ResponseEntity<ReviewResponse> getReviewDTOForEditing(@PathVariable Long productId) {
        return ResponseEntity.ok(shopService.getReviewDTOForEditing(productId));
    }

    @PatchMapping(value = "/{productId}/edit-review", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> editReview(@RequestPart(name = "comment") @Valid CommentRequest comment,
                                        @RequestPart(name = "files", required = false) List<MultipartFile> files,
                                        @PathVariable Long productId) {
        shopService.editExistingReview(productId, comment.getComment(), files);
        return ResponseEntity.ok("Ваш отзыв изменен!");
    }

    @DeleteMapping(value = "/{productId}/delete-rating", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> deleteRating(@PathVariable Long productId) {
        shopService.deleteRating(productId);
        return ResponseEntity.ok("Оценка удалена");
    }

    @DeleteMapping(value = "/{productId}/delete-review", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<?> deleteReview(@PathVariable Long productId) {
        shopService.deleteReview(productId);
        return ResponseEntity.ok("Отзыв удален");
    }

}
