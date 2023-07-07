package com.abhi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.abhi.dao.CustomerRepository;
import com.abhi.entity.Customer;

public class CustomerDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetching customer from database
		Customer customer = customerRepository.getcustomerByName(username);
		
		if(customer==null) {
			throw new UsernameNotFoundException("Could not found Custome !!");
		}
		
		CustomCustomerDetails customCustomerDetails = new CustomCustomerDetails(customer);
		return customCustomerDetails;
	}

}
