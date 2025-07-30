package com.enotes.api.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil
{
	private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

	private static final String SECRET = "supersecretkeyforjwtshouldbelongenough";

	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	public String generateToken(String email)
	{
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token)
	{
		return parseClaims(token).getSubject();
	}

	public boolean isTokenExpired(String token)
	{
		return parseClaims(token).getExpiration().before(new Date());
	}

	public boolean validateToken(String token, String email)
	{
		final String extractedEmail = extractUsername(token);
		return extractedEmail.equals(email) && !isTokenExpired(token);
	}

	private Claims parseClaims(String token)
	{
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
