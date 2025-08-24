package shopping.com.QuickBuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);

            if (count != null && count > 0) {
                session.setAttribute("username", username);
                response.put("success", true);
                response.put("redirect", "/home.html");  
            } else {
                response.put("success", false);
                response.put("message", "Invalid username or password");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error during login: " + e.getMessage());
        }

        return response;
    }
}
