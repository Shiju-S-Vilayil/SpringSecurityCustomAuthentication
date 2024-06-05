package com.SBSecurityCustomAuthProvider.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.SBSecurityCustomAuthProvider.model.Customer;
import com.SBSecurityCustomAuthProvider.respository.CustomRepo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	CustomRepo custrepo;
	
	@Autowired
	PasswordEncoder passencod;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//Get User entered username
		String username=authentication.getName();
		//Get User entered password
		String password=authentication.getCredentials().toString();
		//Fetch Customer info from Database
		List<Customer> customer=custrepo.findByUsername(username);
		if(customer.size()>0)
		{
			//Checking passwords using Hashing Algorithm
				if(passencod.matches(password, customer.get(0).getPassword()))
					{
						List<GrantedAuthority>  authorities=new ArrayList<>();
						authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
						return new UsernamePasswordAuthenticationToken(username,password,authorities);
					}
				else
				{
					throw new BadCredentialsException("Password does not match");
				}
		}
		else
		{
			throw new BadCredentialsException("Username  not found");
		}
		 
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
