package com.clinic.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.app.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
}
