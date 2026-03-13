package com.example.security;


import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

	private final String SECRET="my-secret-key-my-secret-key-my-secret-key";
	
	private final SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(String userName) {
		
		return Jwts.builder().subject(userName).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+360000))
				.signWith(key).compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
	}
	
}
