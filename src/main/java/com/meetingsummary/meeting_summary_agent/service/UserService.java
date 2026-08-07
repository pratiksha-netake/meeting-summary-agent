package com.meetingsummary.meeting_summary_agent.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.meetingsummary.meeting_summary_agent.dao.UserRepository;
import com.meetingsummary.meeting_summary_agent.dto.JwtResponse;
import com.meetingsummary.meeting_summary_agent.dto.LoginRequest;
import com.meetingsummary.meeting_summary_agent.dto.RegisterRequest;
import com.meetingsummary.meeting_summary_agent.model.User;
import com.meetingsummary.meeting_summary_agent.security.JwtUtil;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.jwtUtil=jwtUtil;
	}
	
	
	public String register(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		
		User user = new User();
		
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
		
		return " User Registered Successfully ";
	}
	
	public JwtResponse login(LoginRequest request) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()
				)
				);
		
		String token= jwtUtil.generateToken(request.getEmail());
		
		return new JwtResponse(
				token,
				"Login Successful"
				);
	}
	
	

}

