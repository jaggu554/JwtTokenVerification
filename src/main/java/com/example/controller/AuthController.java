package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AuthRequestDto;
import com.example.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private  JwtService jwtService;
	
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequestDto request) {
		
		authenticationManager.authenticate(
				
				new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword())
				
				);
		
		return jwtService.generateToken(request.getUserName());
		
	}
}
