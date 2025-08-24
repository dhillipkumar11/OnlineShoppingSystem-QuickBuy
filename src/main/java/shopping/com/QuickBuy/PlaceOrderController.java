package shopping.com.QuickBuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class PlaceOrderController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/place")
    public Map<String, Object> placeOrder(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                username = "testuser";
                session.setAttribute("username", username);
            }

            Object cartObj = session.getAttribute("cart");
            if (cartObj == null) {
                return Map.of("success", false, "message", "No items to place order!");
            }

            if (!(cartObj instanceof Map)) {
                return Map.of("success", false, "message", "Cart data invalid!");
            }

            Map<Integer, Product> cart = (Map<Integer, Product>) cartObj;
            if (cart.isEmpty()) {
                return Map.of("success", false, "message", "No items to place order!");
            }

            String sql = "INSERT INTO orders (username, product_id, product_name, quantity, price, total) VALUES (?, ?, ?, ?, ?, ?)";

            final String usernameFinal = username;
            int[][] updateCounts = jdbcTemplate.batchUpdate(sql, cart.values(), cart.size(), (ps, product) -> {
                ps.setString(1, usernameFinal);
                ps.setInt(2, product.getId());
                ps.setString(3, product.getName());
                ps.setInt(4, product.getQuantity());
                ps.setDouble(5, product.getPrice());
                ps.setDouble(6, product.getTotalPrice());
            });


            System.out.println("Insert counts: " + java.util.Arrays.toString(updateCounts));

            session.removeAttribute("cart");

            return Map.of("success", true, "message", "Order placed successfully!");
        } catch (Exception e) {
            System.err.println("Order placing error: " + e.getMessage());
            e.printStackTrace();
            return Map.of("success", false, "message", "Error placing order: " + e.getMessage());
        }
    }

    }

