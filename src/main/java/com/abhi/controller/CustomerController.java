package com.abhi.controller;

import java.security.Principal;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhi.dao.CustomerRepository;
import com.abhi.dao.TransactionRepository;
import com.abhi.entity.Customer;
import com.abhi.entity.Transaction;
import com.smart.helper.Message;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;  
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String customerName = principal.getName();
		//System.out.println(" CUSTOMERNAME "+customerName);
		
		Customer customer = customerRepository.getcustomerByName(customerName);
		//System.out.println("Customer "+ customer);
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
		model.addAttribute("transaction",new Transaction());
		
		return "normal/withdrawal";
	}
	@GetMapping("/deposit")
	public String deposit( Model model) {
		model.addAttribute("title","Deposit - KDFC Bank");
		model.addAttribute("transaction",new Transaction());
		
		return "normal/deposit";
	}
	
	
	//withdrawal transaction
	@PostMapping("/process-transaction")
	public String processTransaction(@ModelAttribute Transaction transaction,
			Principal principal,HttpSession session) {
		
		try {

			String customerName = principal.getName();
			
			Customer customer = customerRepository.getcustomerByName(customerName);
			
			transaction.setCustomer(customer);
			
			System.out.println("Added to database");
			if(customer.getBalance()>transaction.getAmt()) {
				transaction.setDate(new Date(System.currentTimeMillis()));
				transaction.setTransac("Withdraw");
				customer.setBalance(customer.getBalance()-transaction.getAmt());
				transaction.settBalance(customer.getBalance());
				customer.getTransaction().add(transaction);
				this.customerRepository.save(customer);
				System.out.println("transaction :"+transaction);
			session.setAttribute("message", new Message("Successful", "success"));
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Failed", "danger"));

		}
		return "normal/withdrawal";
	}
	
	//deposit transaction
	@PostMapping("/process-transactionDeposit")
	public String processTransactionDeposit(@ModelAttribute Transaction transaction,
			Principal principal,HttpSession session) {
		
		try {

			String customerName = principal.getName();
			
			Customer customer = customerRepository.getcustomerByName(customerName);
			
			transaction.setCustomer(customer);
			
			System.out.println("Added to database");
			if(transaction.getAmt()<25000) {
				transaction.setDate(new Date(System.currentTimeMillis()));
				transaction.setTransac("Deposit");
				customer.setBalance(customer.getBalance()+transaction.getAmt());
				transaction.settBalance(customer.getBalance());

				
				customer.getTransaction().add(transaction);
				this.customerRepository.save(customer);
				System.out.println("transaction :"+transaction);
			session.setAttribute("message", new Message("Successful", "success"));
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Failed", "danger"));

		}
		return "normal/deposit";
	}
	
	

	//show ministatement
	@GetMapping("/show-miniStatement/{page}")
	public String miniStatement(@PathVariable("page") Integer page, Model model,Principal principal){
		model.addAttribute("title","MiniStatement - KDFC Bank");
//		String customerName = principal.getName();
//		
//		Customer customer = customerRepository.getcustomerByName(customerName);
//		
//		transaction.setCustomer(customer);
//		
//		List<Transaction> transaction1=customer.getTransaction();
//		System.out.println("transaction :"+transaction1);
		String customerName = principal.getName();
		Customer customer= this.customerRepository.getcustomerByName(customerName);
		
		Pageable pageable= PageRequest.of(page,5);
		
		Page<Transaction> transactions = this.transactionRepository.findTransactionByCustomer(customer.getId(),pageable);
		model.addAttribute("transaction",transactions);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",transactions.getTotalPages());
		
		return "normal/show_miniStatement";
	}
	
	
}
