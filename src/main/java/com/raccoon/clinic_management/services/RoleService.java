package com.raccoon.clinic_management.services;

import java.util.List;

import com.raccoon.clinic_management.entity.Role;

public interface RoleService {
	public Role saveRole(Role role);
	public Role findById(int id);
	public List<Role> findAll();
	public Role updateRole(Role role);
	public void deleteRole(int id);
}
