package nicstore.dto.product;

import lombok.*;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String images;
    private Integer quantity;
    private BigDecimal price;
}
