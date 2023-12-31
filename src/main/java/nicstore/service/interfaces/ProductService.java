package nicstore.service.interfaces;

import nicstore.models.Category;
import nicstore.models.Product;
import javax.transaction.Transactional;
import java.util.List;

public interface ProductService {
    Product findProductById(Long id);

    List<Product> findProductsByCategory(Category category);

    void updateProductQuantity(Long productId, int newQuantity);
    @Transactional
    void saveProduct(Product product);
    @Transactional
    void deleteProduct(Product product);
}
