package sd.softdeving.orders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    public Product(Long productId, String productName){
        this.productId = productId;
        this.productName = productName;
    }

    @Override
    public String toString(){
        return "Product: " + productName;
    }
}
