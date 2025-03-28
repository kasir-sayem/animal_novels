package com.animalnovels.controller; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.animalnovels.model.User;
import com.animalnovels.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication) {
        if (authentication != null) {
            // Your UserDetailsServiceImpl adds "ROLE_" prefix to roles
            // So we need to check for "ROLE_ADMIN" specifically
            boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
                
            if (isAdmin) {
                return "redirect:/admin";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("user") User user,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "auth/register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken!");
            return "auth/register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email is already in use!");
            return "auth/register";
        }

        
        user.setRole("USER");
        userService.save(user);

        return "redirect:/login?registered";
    }
}