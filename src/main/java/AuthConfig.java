import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthConfig {
    private static final Logger logger = LoggerFactory.getLogger(AuthConfig.class);
    
    public static class AuthCredentials {
        private final String username;
        private final String password;
        
        public AuthCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
    }
    
    public static AuthCredentials getAuthCredentials() {
        String username = System.getProperty("USERNAME");
        String password = System.getProperty("PASSWORD");

        if (username == null || username.isEmpty()) {
            String errorMsg = "USERNAME не задан. Передайте параметр при запуске: -DUSERNAME=your_username";
            logger.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        
        if (password == null || password.isEmpty()) {
            String errorMsg = "PASSWORD не задан. Передайте параметр при запуске: -DPASSWORD=your_password";
            logger.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
        
        return new AuthCredentials(username, password);
    }
}