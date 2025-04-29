package com.clinic.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clinic.app.models.Doctor;
import com.clinic.app.repos.DoctorRepository;

@Controller
public class DoctorController {
	@Autowired
	DoctorRepository doctorRepository;
	
	//GETS
	@GetMapping("/doctors")
	public String index(Model model) {
		model.addAttribute("doctors",doctorRepository.findAll());
		return "doctors/list";
	}
	
	@GetMapping("/doctors/edit/{id}")
	public String edit(@PathVariable("id")Long id, Model model) {
		model.addAttribute("doctor",doctorRepository.findById(id).orElseThrow());
		return "doctors/edit";
	}	
	
	//POSTS
	@PostMapping("/doctors/update/{id}")
	public String update(@PathVariable("id")Long id, @ModelAttribute Doctor doctor) {
		doctor.setId(id);
		doctorRepository.save(doctor);
		return "redirect:/doctors";
	}
	@Transactional
	@PostMapping("/doctors")
	public String create(@ModelAttribute Doctor doctor) {
		doctorRepository.save(doctor);
		return "redirect:/doctors";
	}	
	
	@GetMapping("/doctors/new")
	public String showCreateForm(Model model) {
	    model.addAttribute("doctor", new Doctor());
	    return "doctors/create";
	}	
	
	
}
