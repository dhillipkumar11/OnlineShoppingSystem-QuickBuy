package shopping.com.QuickBuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SomeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        return jdbcTemplate.update(sql, username, password);
    }
}
