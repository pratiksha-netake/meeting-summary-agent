package com.meetingsummary.meeting_summary_agent.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY="meetingsummarysecretkeymeetingsummarysecretkey";
	
	public static  final long JWT_EXPIRATION=1000*60*60*24;
	
	
	private Key getSignKey() {
		byte[] keyBytes = SECRET_KEY.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	
	public String extractEmail(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token,Claims::getExpiration);
		
	}
	
	public <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
		Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public boolean validateToken(String token,String email) {
		
		String extractEmail= extractEmail(token);
		
		return extractEmail.equals(email) && !isTokenExpired(token);
	}
	
	
}
