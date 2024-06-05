package com.SBSecurityCustomAuthProvider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SBSecurityCustomAuthProvider.model.Customer;
import com.SBSecurityCustomAuthProvider.respository.CustomRepo;

@Service
public class CustomerService 
{
	
	@Autowired
	CustomRepo repo;
	
	@Autowired
	PasswordEncoder Passencode;
	
	
	
	//Save customer details into database
	public Customer saveCustomer(Customer cust)
	{
		cust.setRole("user");
		//Password encryption using BCrypt Hashing Algorithm
		cust.setPassword(Passencode.encode(cust.getPassword()));
		return repo.save(cust);
	}
}
