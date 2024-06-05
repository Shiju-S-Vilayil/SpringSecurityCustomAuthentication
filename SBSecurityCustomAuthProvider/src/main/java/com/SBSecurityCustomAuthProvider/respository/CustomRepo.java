package com.SBSecurityCustomAuthProvider.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SBSecurityCustomAuthProvider.model.Customer;

@Repository
public interface CustomRepo extends JpaRepository<Customer,Integer>
{
	List<Customer> findByUsername(String username);
}
