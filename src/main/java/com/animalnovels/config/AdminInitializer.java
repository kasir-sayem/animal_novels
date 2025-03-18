// package com.animalnovels.config; // Update this package name to match your project structure

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.animalnovels.model.User; // Update to match your User entity package
// import com.animalnovels.repository.UserRepository; // Update to match your repository package

// @Component
// public class AdminInitializer implements CommandLineRunner {
    
//     @Autowired
//     private UserRepository userRepository;
    
//     @Autowired
//     private PasswordEncoder passwordEncoder;
    
//     @Override
//     public void run(String... args) {
//         // Check if admin user exists
//         if (userRepository.findByUsername("admin").isEmpty()) {
//             // Create the admin user if it doesn't exist
//             User admin = new User();
//             admin.setUsername("admin");
//             admin.setPassword(passwordEncoder.encode("admin123"));
//             admin.setEmail("admin@example.com");
//             admin.setRole("ROLE_ADMIN");
            
//             // Save the admin user to the database
//             userRepository.save(admin);
            
//             System.out.println("Admin user created successfully!");
//         } else {
//             System.out.println("Admin user already exists.");
//         }
//     }
// }