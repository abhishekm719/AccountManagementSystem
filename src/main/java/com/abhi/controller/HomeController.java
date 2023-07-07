package com.abhi.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhi.dao.CustomerRepository;
import com.abhi.entity.Customer;
import com.smart.helper.Message;

@Controller
public class HomeController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Home- KDFC Bank");
		return "home";
	}
	
	@RequestMapping("/miniStatement")
	public String miniStatement(Model model) {
		model.addAttribute("title","MiniStatement- KDFC Bank");
		return "miniStatement";
	}
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","Register- KDFC Bank");
		model.addAttribute("customer", new Customer() );
		return "signup";
	}
	
	//registering Customer
	
	@PostMapping("/do_register")
	public String registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result1, 
			@RequestParam(value="agreement",defaultValue="false")boolean agreement,
			Model model,HttpSession session ) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed T&C");
				throw new Exception("You have not agreed T&C");
			}
				
			customer.setRole("ROLE_CUSTOMER");
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			
			if(result1.hasErrors()) {
				System.out.println("ERROR "+ result1.toString());
				model.addAttribute("customer",customer);

				return "signup";
			}
			
			System.out.println("Agreement "+agreement);
			System.out.println("Customer "+customer);
			Customer result=this.customerRepository.save(customer);
			
			model.addAttribute("customer",new Customer());
			session.setAttribute("message", new Message("Successfully Registered ","alert-success") );
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("customer",customer);
			session.setAttribute("message", new Message("Something went wrong ! "+e.getMessage(),"alert-danger") );
			return "signup";
		}
	
	}
	
	
	// login
	@GetMapping("/signin")
		public String customLogin(Model model) {
		model.addAttribute("title", "Login Page");
			return "login";
		}

}
