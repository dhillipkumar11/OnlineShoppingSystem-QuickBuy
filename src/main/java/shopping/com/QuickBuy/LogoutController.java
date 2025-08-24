package shopping.com.QuickBuy;

 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
public class LogoutController {

    @GetMapping("/api/logout")
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        return Map.of("success", true, "message", "Logged out successfully");
    }
}

