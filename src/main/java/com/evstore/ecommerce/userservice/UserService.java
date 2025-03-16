package com.evstore.ecommerce.userservice;

import com.evstore.ecommerce.user.User;
import com.evstore.ecommerce.userrepository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists.");
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, email, hashedPassword, User.Role.USER);
        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password, HttpSession session) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && BCrypt.checkpw(password, userOpt.get().getPassword())) {
            session.setAttribute("user", userOpt.get());  // Store user in session
            return true;
        }

        return false;
    }

    public void logoutUser(HttpSession session) {
        session.invalidate(); // Destroy session
    }
}
