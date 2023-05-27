package semp.nnpia.be.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    @Test
    public void testIsStrongPassword_withStrongPassword_returnsTrue() {
        String strongPassword = "Abcdefg1!";

        boolean result = PasswordValidator.isStrongPassword(strongPassword);

        assertTrue(result);
    }

    @Test
    public void testIsStrongPassword_withWeakPassword_returnsFalse() {
        String weakPassword = "password";

        boolean result = PasswordValidator.isStrongPassword(weakPassword);

        assertFalse(result);
    }

    @Test
    public void testPasswordHashMatch() {
        String password = "password123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        assertTrue(passwordEncoder.matches(password, hashedPassword));
    }

    @Test
    public void testPasswordHashNotMatch() {
        String password = "password123";
        String wrongPassword = "wrongpassword";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        assertFalse(passwordEncoder.matches(wrongPassword, hashedPassword));
    }

    @Test
    public void testPasswordHashUnique() {
        String password = "password123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword1 = passwordEncoder.encode(password);
        String hashedPassword2 = passwordEncoder.encode(password);

        assertNotEquals(hashedPassword1, hashedPassword2);
    }
}