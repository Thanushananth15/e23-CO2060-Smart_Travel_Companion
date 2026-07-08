package com.smarttravel.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.smarttravel.backend.model.User;
import com.smarttravel.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    	return args -> {

        	System.out.println("Seeder started...");

        	if (!userRepository.existsByEmail("admin@smarttravel.com")) {

        		User admin = new User();
            	admin.setName("Admin User");
            	admin.setEmail("admin@smarttravel.com");
            	admin.setPassword(passwordEncoder.encode("admin123"));
            	admin.setRole("ROLE_ADMIN");

            	userRepository.save(admin);

            	System.out.println("Admin inserted successfully!");

            	System.out.println(userRepository.findAll());

        	} else {

            	System.out.println("Admin already exists.");

            	System.out.println(userRepository.findAll());

        	}
    	};
	}
}
