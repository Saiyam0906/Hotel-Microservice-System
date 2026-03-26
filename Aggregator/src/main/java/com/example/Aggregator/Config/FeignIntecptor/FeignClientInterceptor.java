package com.example.Aggregator.Config.FeignIntecptor;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;


@Component
public class FeignClientInterceptor implements RequestInterceptor{

	@Override
	public void apply(RequestTemplate template) {
		// TODO Auto-generated method stub
		
		 Authentication authentication = SecurityContextHolder
	                .getContext()
	                .getAuthentication();
		 
		 if (authentication instanceof JwtAuthenticationToken jwtToken) {
	            template.header(
	                "Authorization",
	                "Bearer " + jwtToken.getToken().getTokenValue()
	            );
	        }
		
	}

}
