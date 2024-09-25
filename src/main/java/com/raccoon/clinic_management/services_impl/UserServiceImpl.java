package com.raccoon.clinic_management.services_impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raccoon.clinic_management.entity.User;
import com.raccoon.clinic_management.repository.UserRepository;
import com.raccoon.clinic_management.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas de validez
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Override
	public User register(User user) {
		String encoderPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encoderPassword);
		return userRepository.save(user);
	}
	
	@Override
	public String login(String username, String password) {
		User user = findByUsername(username);
	    if (!passwordEncoder.matches(password, user.getPassword())) {
	        throw new BadCredentialsException("Invalid credentials");
	    }
	    return generateToken(user);
	}
	
	@Override
	public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
	
	@Override
	public User findById(int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		return user.get();
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User updateUser(User user) {
		Optional<User> dbuser = userRepository.findById(user.getId());

		if (dbuser.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		User existingUser = dbuser.get();
		
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	        String encoderPassword = this.passwordEncoder.encode(user.getPassword());
	        existingUser.setPassword(encoderPassword);
	    }
		
		existingUser.setUsername(user.getUsername());
		existingUser.setRole(user.getRole());
		existingUser.setContact(user.getContact());
		
		return userRepository.save(existingUser);
	}
	
	@Override
	public void deleteUser(int id) {
		Optional<User> dbuser = userRepository.findById(id);

		if (dbuser.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		userRepository.delete(dbuser.get());
	}

}