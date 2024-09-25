package com.raccoon.clinic_management.services_impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raccoon.clinic_management.entity.Role;
import com.raccoon.clinic_management.repository.RoleRepository;
import com.raccoon.clinic_management.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;
	
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	public Role findById(int id) {
		Optional<Role> role = roleRepository.findById(id);

		if (role.isEmpty()) {
			throw new RuntimeException("role not found");
		}

		return role.get();
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	public Role updateRole(Role role) {
		Optional<Role> dbrole = roleRepository.findById(role.getId());

		if (dbrole.isEmpty()) {
			throw new RuntimeException("role not found");
		}

		Role existingRole = dbrole.get();
		existingRole.setName(role.getName());

        return roleRepository.save(existingRole);
	}

	public void deleteRole(int id) {
		Optional<Role> dbrole = roleRepository.findById(id);

		if (dbrole.isEmpty()) {
			throw new RuntimeException("role not found");
		}

		roleRepository.delete(dbrole.get());
	}
	
}
