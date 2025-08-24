package shopping.com.QuickBuy;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderHistoryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/history")
    public Object getOrderHistory(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // User not logged in
            return Map.of("success", false, "message", "User not authenticated");
        }
        String sql = "SELECT product_id, product_name, quantity, price, total FROM orders WHERE username = ?";

        List<OrderItem> orders = jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<OrderItem>() {
            @Override
            public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderItem item = new OrderItem();
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("product_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setTotal(rs.getDouble("total"));
                return item;
            }
        });

        if (orders.isEmpty()) {
            return Map.of("success", true, "orders", orders, "message", "No orders found");
        }
        return Map.of("success", true, "orders", orders);
    }

    // Inner class for order items
    public static class OrderItem {
        private int productId;
        private String productName;
        private int quantity;
        private double price;
        private double total;

        // Getters and setters
        public int getProductId() { return productId; }
        public void setProductId(int productId) { this.productId = productId; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }

        public double getTotal() { return total; }
        public void setTotal(double total) { this.total = total; }
    }
}
