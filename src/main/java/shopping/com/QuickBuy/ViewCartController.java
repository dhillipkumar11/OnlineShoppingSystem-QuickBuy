package shopping.com.QuickBuy;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class ViewCartController {

    @GetMapping
    public List<Product> getCartItems(HttpSession session) {
        try {
            Object cartObj = session.getAttribute("cart");
            if (!(cartObj instanceof Map)) {
                return Collections.emptyList();
            }
            Map<Integer, Product> cart = (Map<Integer, Product>) cartObj;
            if (cart.isEmpty()) {
                return Collections.emptyList();
            }
            // Returning unmodifiable list to avoid accidental changes in session collection
            return Collections.unmodifiableList(List.copyOf(cart.values()));

        } catch (Exception e) {
            // Logging can be added here
            return Collections.emptyList();
        }
    }
}
	