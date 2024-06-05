package com.SBSecurityCustomAuthProvider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SBSecurityCustomAuthProvider.Service.CustomerService;
import com.SBSecurityCustomAuthProvider.model.Customer;


@RestController
public class MainController 
{
	@Autowired
	CustomerService serve;
	
	
	
	@GetMapping("/save")
	public String saveCustomer(@RequestBody Customer customer) 
	{
		String sucess;
		if(serve.saveCustomer(customer)!=null)
		{
			 sucess="SUCCESS";
		}
		else
		{
			 sucess="FAILED";
		}
		return sucess;
	}
	
	
	
}
