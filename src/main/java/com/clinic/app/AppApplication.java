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
        	User userEditor = new User();
        	userEditor.setEmail("admin@example.com");
        	userEditor.setPassword(passwordEncoder.encode("password"));
        	userEditor.setRole("ROLE_EDITOR");
        	userRepository.save(userEditor);
        	
        	User userOwner = new User();
        	userOwner.setEmail("owner@example.com");
        	userOwner.setPassword(passwordEncoder.encode("password"));        	
        	userOwner.setRole("ROLE_USER");
        	userRepository.save(userOwner);
        	
	       	Doctor doctor1 = new Doctor();
	        doctor1.setName("Dr.Mya Mya");
	        doctor1.setPhone("234567567");
	        doctor1.setCreatedBy(userOwner);
            doctorRepo.save(doctor1);

           
       	   Doctor doctor2 = new Doctor();
		   doctor2.setName("Dr.Mary Moe");
		   doctor2.setPhone("234567");
		   doctor2.setCreatedBy(userOwner);
		   doctorRepo.save(doctor2);
           

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
