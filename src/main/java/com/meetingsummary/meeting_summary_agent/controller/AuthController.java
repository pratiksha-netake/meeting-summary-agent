package com.meetingsummary.meeting_summary_agent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetingsummary.meeting_summary_agent.dto.JwtResponse;
import com.meetingsummary.meeting_summary_agent.dto.LoginRequest;
import com.meetingsummary.meeting_summary_agent.dto.RegisterRequest;
import com.meetingsummary.meeting_summary_agent.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService=userService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request){
		String response=userService.register(request);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request){
			
			JwtResponse response = userService.login(request);
			
			return ResponseEntity.ok(response);
	}
}

