package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
public class Cart {

    @Id
    private Long id;

    private Long userID;

    private List<Product> productList;

    private double totalPrice;

    private double withShipping;

    private int numberOfProducts;

}