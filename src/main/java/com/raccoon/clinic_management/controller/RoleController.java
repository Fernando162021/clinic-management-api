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

import com.raccoon.clinic_management.entity.Role;
import com.raccoon.clinic_management.services_impl.RoleServiceImpl;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping("/save")
	public Role save(@RequestBody Role role) {
		return roleService.saveRole(role);
	}
	
	@GetMapping("/findAll")
	public List<Role> findAll() {
		return roleService.findAll();
	}
	
	@GetMapping("/findById")
	public Role findById(@RequestParam int id) {
		return roleService.findById(id);
	}
	
	@PutMapping("/update") 
	public Role update(@RequestBody Role role) {
		return roleService.updateRole(role);
	}
	
	@DeleteMapping("/deleteById")
	public void delete(@RequestParam int id) {
		roleService.deleteRole(id);
	}
	
}