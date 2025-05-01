package com.clinic.app.controllers.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.app.models.Doctor;
import com.clinic.app.models.User;
import com.clinic.app.repos.DoctorRepository;
import com.clinic.app.repos.UserRepository;
import com.clinic.app.services.PolicyService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorApiController {

    @Autowired
    private DoctorRepository doctorRepository;

	@Autowired
	PolicyService policyService;
    
	@Autowired
	UserRepository userRepository;
	
    // Get all doctors
    @GetMapping
    public ResponseEntity<?> getAllDoctors(Authentication authentication) {
    	System.out.println("current log in user name:"+authentication.getPrincipal());
		User currentUser = userRepository.findByEmail((String)authentication.getPrincipal()).orElseThrow(()->new RuntimeException("Cannot Find Current User")); // Get the currently logged-in user
		
		
		List<Doctor> list =  doctorRepository.findAll().stream().filter(m->policyService.isAuthorized(currentUser, "READ",m)).toList();
		return list.isEmpty()
		        ? ResponseEntity.notFound().build()
		        : ResponseEntity.ok(list);
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