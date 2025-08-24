package shopping.com.QuickBuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private SomeDAO someDAO;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            int row = someDAO.registerUser(user.getUsername(), user.getPassword());

            if (row > 0) {
                response.put("success", true);
                response.put("message", "Registration Successful! Please login.");
            } else {
                response.put("success", false);
                response.put("message", "Registration Failed. Try again.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }
}
