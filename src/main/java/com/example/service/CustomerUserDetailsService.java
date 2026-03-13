package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Users;
import com.example.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=userRepo.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		
		return User.withUsername(username).password(user.getPassword()).roles(user.getRole()).build();
	}
	
	

}
