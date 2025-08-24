package shopping.com.QuickBuy;

 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ViewProductsController {

    private final List<Product> productList = Arrays.asList(
        new Product(1, "T-shirt", 499.0),
        new Product(2, "Shoes", 1299.0),
        new Product(3, "Backpack", 899.0)
    );

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productList;
    }
}

