package com.raccoon.clinic_management.services;

import java.util.List;

import com.raccoon.clinic_management.entity.User;

public interface UserService {
	public User register(User user);
	public String login(String username, String password);
	public User findById(int id);
	public User findByUsername(String username);
	public List<User> findAll();
	public User updateUser(User user);
	public void deleteUser(int id);
}