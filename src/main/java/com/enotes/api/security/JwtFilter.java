package com.enotes.api.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enotes.api.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter
{
	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException
	{
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer "))
		{
			String token = authHeader.substring(7);
			String email = jwtUtil.extractUsername(token);

			if (email != null
					&& SecurityContextHolder.getContext().getAuthentication() == null)
			{
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);

				if (jwtUtil.validateToken(token, userDetails.getUsername()))
				{
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.getAuthorities());

					SecurityContextHolder.getContext()
							.setAuthentication(authenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
