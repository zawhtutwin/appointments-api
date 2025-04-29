package com.clinic.app.controllers.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.app.models.Doctor;
import com.clinic.app.repos.DoctorRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorApiController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new doctor
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        doctor.setId(null);
    	return doctorRepository.save(doctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setName(doctorDetails.getName());
            doctor.setPhone(doctorDetails.getPhone());
            Doctor updatedDoctor = doctorRepository.save(doctor);
            return ResponseEntity.ok(updatedDoctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}