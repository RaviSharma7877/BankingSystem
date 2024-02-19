package com.RSstudies.Config;


import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("inside doFilter....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(authentication);
		if (null != authentication) {
			System.out.println("auth.getAuthorities " + authentication.getAuthorities());
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
			String jwt = Jwts.builder()
					.setIssuer("Ram")
					.setSubject("JWT Token")
					.claim("username", authentication.getName())
					.claim("authorities", populateAuthorities(authentication.getAuthorities()))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + 30000000)) // millisecond expiration time of around 8
																				
					.signWith(key).compact();
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
			System.out.println(jwt);
		}
		filterChain.doFilter(request, response);
		System.out.println("jwt generated.........");
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();

		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/api/signIn");
	}
}