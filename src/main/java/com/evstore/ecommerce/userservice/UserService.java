package com.evstore.ecommerce.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.evstore.ecommerce.user.User;
import com.evstore.ecommerce.userrepository.UserRepository;

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

    public void registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User already exists.");
        }
        String hashedPassword = passwordEncoder.encode(password); 
        User user = new User(username, email, hashedPassword, User.Role.USER);
        userRepository.save(user);
    }

    public boolean loginUser(String username, String password, HttpSession session) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("Stored Hash: " + user.getPassword());
            System.out.println("Raw Input: " + password);
            System.out.println("Match Result: " + passwordEncoder.matches(password, user.getPassword()));
    
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("loggedInUser", user.getUsername());
                session.setAttribute("userRole", user.getRole().name());
                return true;
            }
        }
    
        return false;
    }

    public void logoutUser(HttpSession session) {
        session.invalidate();
    }

  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
