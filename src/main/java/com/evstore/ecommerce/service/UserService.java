package com.evstore.ecommerce.service;

import java.util.Optional;

import com.evstore.ecommerce.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.evstore.ecommerce.model.User;
import com.evstore.ecommerce.model.Address;
import com.evstore.ecommerce.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService { 

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) throw new RuntimeException("User already exists.");
        if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Email already exists.");
        if (!isValidEmail(user.getEmail())) throw new RuntimeException("Invalid email format.");

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

//    public boolean loginUser(String username, String password, HttpSession session) {
//        Optional<User> userOpt = userRepository.findByUsername(username);
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            System.out.println("Stored Hash: " + user.getPassword());
//            System.out.println("Raw Input: " + password);
//            System.out.println("Match Result: " + passwordEncoder.matches(password, user.getPassword()));
//
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                session.setAttribute("loggedInUser", user.getUsername());
//                //session.setAttribute("userRole", user.getRole().name());
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    public void logoutUser(HttpSession session) {
//        session.invalidate();
//    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Allow user login via username or email
        User user = userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmail));
        return new CustomUserDetails(user);
    }
}
