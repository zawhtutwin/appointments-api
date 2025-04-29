package com.clinic.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.clinic.app.models.Doctor;
import com.clinic.app.models.User;
import com.clinic.app.repos.DoctorRepository;
import com.clinic.app.repos.UserRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
    @Bean
    CommandLineRunner initData(DoctorRepository doctorRepo,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        return args -> {
        	doctorRepo.deleteAll();
        	userRepository.deleteAll();
        	User user = new User();
        	user.setEmail("user@example.com");
        	user.setPassword(passwordEncoder.encode("password"));
        	
        	user.setRole("ROLE_USER");
        	userRepository.save(user);
        	
        	Doctor doctor = new Doctor();
           doctor.setName("Dr.Mary Moe");
           doctor.setPhone("234567");
           doctorRepo.save(doctor);
        };
    }
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8081")  // Allow CORS for the entire application
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
}
