/** 
package com.evstore.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.evstore.ecommerce.service.UserService;

@Configuration
public class AdminInitializer {

    @Bean
    @Transactional 
    public CommandLineRunner createAdminUser(UserService userService) {
        return args -> {

            //create admin in the first time
            if (userService.findUserByUsername("admin") == null) {
                userService.registerAdmin("admin", "admin@example.com", "admin123"); 
                System.out.println("Admin user created: admin@example.com / admin123");
            } else {
                System.out.println("Admin user already exists. Skipping creation.");
            }
        };
    }
}
*/