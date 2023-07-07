package com.abhi.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhi.dao.CustomerRepository;
import com.abhi.entity.Customer;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	// method to add common data for response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String customerName = principal.getName();
		System.out.println(" CUSTOMERNAME "+customerName);
		
		Customer customer = customerRepository.getcustomerByName(customerName);
		System.out.println("Customer "+ customer);
		model.addAttribute("customer",customer);
	}
	
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title","Profile - KDFC Bank");
		return "normal/customer_dashboard";
	}
	
	@GetMapping("/balance")
	public String balance( Model model) {
		model.addAttribute("title","Balance - KDFC Bank");
		return "normal/balance";
	}
	
	@GetMapping("/withdrawal")
	public String withdrawal( Model model) {
		model.addAttribute("title","Withdrawal - KDFC Bank");
		
		
		
		return "normal/withdrawal";
	}
	
	

}
