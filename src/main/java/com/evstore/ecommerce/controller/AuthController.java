
package com.evstore.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/auth/register")
    public String showRegisterPage() {
        return "register"; // This looks for register.html in `src/main/resources/templates`
    }

    @GetMapping("/auth/login")
    public String showLoginPage() {
        return "login"; // This maps to src/main/resources/templates/login.html
    }
}
