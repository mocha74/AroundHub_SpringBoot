package studio.thinkground.aroundhub.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {

    @Id
    String productId;

    String productName;

    Integer productPrice;

    Integer productStock;
}
