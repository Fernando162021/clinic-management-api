package com.raccoon.clinic_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raccoon.clinic_management.entity.Contact;
import com.raccoon.clinic_management.services_impl.ContactServiceImpl;

@RestController
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	private ContactServiceImpl contactService;
	
	@PostMapping("/save")
	public Contact save(@RequestBody Contact contact) {
		return contactService.saveContact(contact);
	}
	
	@GetMapping("/findAll")
	public List<Contact> findAll() {
		return contactService.findAll();
	}
	
	@GetMapping("/findById")
	public Contact findById(@RequestParam int id) {
		return contactService.findById(id);
	}
	
	@PutMapping("/update") 
	public Contact update(@RequestBody Contact contact) {
		return contactService.updateContact(contact);
	}
	
	@DeleteMapping("/deleteById")
	public void delete(@RequestParam int id) {
		contactService.deleteContact(id);
	}
	
}

