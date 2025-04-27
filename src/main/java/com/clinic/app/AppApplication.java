package com.clinic.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clinic.app.models.Doctor;
import com.clinic.app.repos.DoctorRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
    @Bean
    CommandLineRunner initData(DoctorRepository doctorRepo) {
        return args -> {
           Doctor doctor = new Doctor();
           doctor.setName("Dr.Mary Moe");
           doctor.setPhone("234567");
           doctorRepo.save(doctor);
        };
    }
	
}
