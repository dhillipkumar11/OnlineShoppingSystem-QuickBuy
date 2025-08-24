package shopping.com.QuickBuy;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class RemoveCartController {

    @DeleteMapping("/remove/{id}")
    public Map<String, Object> removeFromCart(@PathVariable int id, HttpSession session) {
        Map<Integer, Product> cart = (Map<Integer, Product>) session.getAttribute("cart");

        if (cart != null && cart.containsKey(id)) {
            cart.remove(id);
            session.setAttribute("cart", cart);
            return Map.of("success", true, "message", "Product removed from cart");
        } else {
            return Map.of("success", false, "message", "Product not found in cart");
        }
    }
}

