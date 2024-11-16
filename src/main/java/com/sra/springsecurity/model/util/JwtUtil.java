package com.sra.springsecurity.model.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String tokenSecret;
	@Value("${jwt.issuer}")
	private String issuer;
	
	
	 public String generateToken(UserDetails userDetails) {
		 
		 //if we want to add more values add as claims i.e private cliams
//		 Map<String, Object> claims = new HashMap<>();
//	        claims.put("email", userDetails.get);
//	        claims.put("role", role);
//	        claims.put("userId", userId);
		 
	        return Jwts.builder()
	            .setSubject(userDetails.getUsername())
//	            .setClaims(cliams);
	            .setIssuedAt(new Date())
	            .setIssuer(issuer)
	            .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *60*10))
	            .signWith(SignatureAlgorithm.HS256, tokenSecret)
	            .compact();
	    }
	 public String extractUsername(String token) {
		 return extractClaims(token).getSubject();
	 }
	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
	}
	public boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
	
	public boolean validateToken(String username,String token) {
		return (extractUsername(token).equals(username) && !isTokenExpired(token));
	}
}
