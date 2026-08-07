package com.meetingsummary.meeting_summary_agent.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter  extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterchain)throws ServletException , IOException {
		
		String path = request.getServletPath();
		
		if(path.startsWith("/auth/")) { 

			        filterchain.doFilter(request,response);
			        return;
			    }
		String authHeader=request.getHeader("Authorization");
		
		String token=null;
		String email= null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			try {
			email=jwtUtil.extractEmail(token);
			}catch(Exception e) {
				 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		            return;
			}
		}
		
		
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
			
			if(jwtUtil.validateToken(token,userDetails.getUsername())) {
				
				UsernamePasswordAuthenticationToken authentication=
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
								);
				
				authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterchain.doFilter(request,response);
		

}
}
