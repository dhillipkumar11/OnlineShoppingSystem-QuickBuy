package shopping.com.QuickBuy;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class AddCartController {

    private static final Map<Integer, Product> PRODUCTS = Map.of(
        1, new Product(1, "T-shirt", 499.0),
        2, new Product(2, "Shoes", 1299.0),
        3, new Product(3, "Backpack", 899.0)
    );

    @PostMapping("/add")
    public Map<String, Object> addToCart(@RequestParam int id, HttpSession session) {
        Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");
        if (cart == null) cart = new HashMap<>();

        Product prod = PRODUCTS.get(id);
        if (prod == null) {
            return Map.of("success", false, "message", "Product ID not found");
        }

        Product cartItem = cart.get(id);
        if (cartItem == null) {
            // Using constructor with quantity=1 for new cart item
            cartItem = new Product(prod.getId(), prod.getName(), 1, prod.getPrice());
        } else {
            // Increase quantity for existing cart item
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cart.put(id, cartItem);
        session.setAttribute("cart", cart);

        return Map.of(
            "success", true,
            "product", cartItem,
            "message", "Product added to cart successfully!"
        );
    }
}
