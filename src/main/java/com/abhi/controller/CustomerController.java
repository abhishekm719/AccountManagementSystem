package com.abhi.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.dao.AccountRepository;
import com.abhi.dao.CustomerRepository;
import com.abhi.dao.FDRepository;
import com.abhi.dao.MoneyTransferRepository;
import com.abhi.dao.NomineeRepository;
import com.abhi.dao.TransactionRepository;
import com.abhi.dao.UPIRepository;
import com.abhi.entity.Account;
import com.abhi.entity.Customer;
import com.abhi.entity.FixedDeposit;
import com.abhi.entity.MoneyTransfer;
import com.abhi.entity.Nominee;
import com.abhi.entity.Transaction;
import com.abhi.entity.UPI;
import com.smart.helper.Message;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;  
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private  NomineeRepository nomineeRepository;
	
	@Autowired
	private MoneyTransferRepository moneyTransferRepository;
	
	@Autowired
	private FDRepository fdRepository;

	@Autowired
	private UPIRepository upiRepository;
	
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
	
	@PostMapping("/withdrawal/{accNo}")
	public String withdrawal(@PathVariable("accNo")Integer accNo, Model model) {
		model.addAttribute("title","Withdrawal - KDFC Bank");
		
		Account account = this.accountRepository.findById(accNo).get();
		model.addAttribute("account",account);
		return "normal/withdrawal";
	}
	@PostMapping("/deposit/{accNo}")
	public String deposit(@PathVariable("accNo")Integer accNo, Model model) {
		model.addAttribute("title","Deposit - KDFC Bank");
		Account account = this.accountRepository.findById(accNo).get();
		model.addAttribute("account",account);	
		
		return "normal/deposit";
	}
	
	@PostMapping("/upi/{accNo}")
	public String upi(@PathVariable("accNo")Integer accNo, Model model) {
		model.addAttribute("title","UPI - KDFC Bank");
		
		UPI upi = this.upiRepository.findById(accNo).get();
		System.out.println("Data :"+upi.getVaddress());
		Account account = this.accountRepository.findById(accNo).get();
		model.addAttribute("account",account);
		model.addAttribute("upi",upi);
		return "normal/upi";
	}
	
	
	//withdrawal transaction
//	@PostMapping("/process-transaction1")
//	public String processTransaction1(@ModelAttribute Transaction transaction,
//			Principal principal,HttpSession session,@ModelAttribute Account account) {
//		
//		try {
//
//			String customerName = principal.getName();
//			
//			Customer customer = customerRepository.getcustomerByName(customerName);
//			
//			transaction.setCustomer(customer);
//			
//			
//			System.out.println("Added to database");
//			if(account.getaBalance()>transaction.getAmt()) {
//				transaction.setDate(new Date(System.currentTimeMillis()));
//				transaction.setTransac("Withdraw");
//				
//				account.setaBalance(account.getaBalance()-transaction.getAmt());
//				transaction.settBalance(account.getaBalance());
//				
////				customer.setBalance(customer.getBalance()-transaction.getAmt());
////				transaction.settBalance(customer.getBalance());
//				customer.getTransaction().add(transaction);
//				this.customerRepository.save(customer);
//				System.out.println("transaction :"+transaction);
//			session.setAttribute("message", new Message("Successful", "success"));
//			}else {
//				throw new Exception();
//			}
//			
//		} catch (Exception e) {
//			System.out.println("ERROR "+e.getMessage());
//			e.printStackTrace();
//			session.setAttribute("message", new Message("Failed", "danger"));
//
//		}
//		return "normal/withdrawal";
//	}
//	
//	

	//withdrawal transaction
	@PostMapping("/process-transaction")
	public String processTransaction( Transaction transaction,
			Principal principal,HttpSession session,@ModelAttribute Account account) {
		
		try {

			String customerName = principal.getName();
			
			Customer customer = customerRepository.getcustomerByName(customerName);
		
			Account account2 = accountRepository.findById(account.getAccNo()).get();
			
			if(account2.getaBalance()>transaction.getAmt()) {
				transaction.setDate(new Date(System.currentTimeMillis()));
				transaction.setTransac("Withdraw");
				
				account2.setaBalance(account2.getaBalance()-transaction.getAmt());
				transaction.settBalance(account2.getaBalance());
				
//				customer.setBalance(customer.getBalance()-transaction.getAmt());
//				transaction.settBalance(customer.getBalance());
				customer.getTransaction().add(transaction);
				transaction.setCustomer(customer);
				account.setCustomer(customer);
				this.customerRepository.save(customer);
				
				this.accountRepository.save(account2);
				
				System.out.println("Added to database");
//				System.out.println("transaction :"+transaction);
			session.setAttribute("message", new Message("Successful", "success"));
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Failed", "danger"));

		}
		return "redirect:/customer/newAccount";
	}
	
	
	
	
	
	
//	//deposit transaction
//	@PostMapping("/process-transactionDeposit")
//	public String processTransactionDeposit(@ModelAttribute Transaction transaction,
//			Principal principal,HttpSession session, Account account) {
//		
//		try {
//
//			String customerName = principal.getName();
//			
//			Customer customer = customerRepository.getcustomerByName(customerName);
//			
//			transaction.setCustomer(customer);
//			
//			System.out.println("Added to database");
//			if(transaction.getAmt()<25000) {
//				transaction.setDate(new Date(System.currentTimeMillis()));
//				transaction.setTransac("Deposit");
//				account.setaBalance(account.getaBalance()+transaction.getAmt());
//				transaction.settBalance(account.getaBalance());
////				customer.setBalance(customer.getBalance()+transaction.getAmt());
////				transaction.settBalance(customer.getBalance());
//
//				
//				customer.getTransaction().add(transaction);
//				this.customerRepository.save(customer);
//				System.out.println("transaction :"+transaction);
//			session.setAttribute("message", new Message("Successful", "success"));
//			}else {
//				throw new Exception();
//			}
//			
//		} catch (Exception e) {
//			System.out.println("ERROR "+e.getMessage());
//			e.printStackTrace();
//			session.setAttribute("message", new Message("Failed", "danger"));
//
//		}
//		return "normal/deposit";
//	}
//	
	
	
	
	@PostMapping("/process-transactionDeposit")
	public String processTransactionDeposit( Transaction transaction,
			Principal principal,HttpSession session,@ModelAttribute Account account) {
		
		try {

			String customerName = principal.getName();
			
			Customer customer = customerRepository.getcustomerByName(customerName);
		
			Account account2 = accountRepository.findById(account.getAccNo()).get();
			
			if(transaction.getAmt()<25000) {
				transaction.setDate(new Date(System.currentTimeMillis()));
				transaction.setTransac("Deposit");
				
				account2.setaBalance(account2.getaBalance()+transaction.getAmt());
				transaction.settBalance(account2.getaBalance());

//				customer.setBalance(customer.getBalance()-transaction.getAmt());
//				transaction.settBalance(customer.getBalance());
				customer.getTransaction().add(transaction);
				transaction.setCustomer(customer);
				account.setCustomer(customer);
				this.customerRepository.save(customer);
				
				this.accountRepository.save(account2);
				
				System.out.println("Added to database");
//				System.out.println("transaction :"+transaction);
			session.setAttribute("message", new Message("Successful", "success"));
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("Failed", "danger"));

		}
		return "redirect:/customer/newAccount";
	}
	
	
	

	//show ministatement
	@GetMapping("/show-miniStatement/{page}")
	public String miniStatement(@PathVariable("page") Integer page, Model model,Principal principal){
		model.addAttribute("title","MiniStatement - KDFC Bank");
		
		String customerName = principal.getName();
		Customer customer= this.customerRepository.getcustomerByName(customerName);
		
		Pageable pageable= PageRequest.of(page,5);
		
		Page<Transaction> transactions = this.transactionRepository.findTransactionByCustomer(customer.getId(),pageable);
		model.addAttribute("transaction",transactions);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",transactions.getTotalPages());
		
		return "normal/show_miniStatement";
	}
	
	
	//Overview
		@GetMapping("/newAccount")
		public String newAccountForm(Model model,Principal principal){
			model.addAttribute("title","Account Details - KDFC Bank");
			String customerName = principal.getName();
			Customer customer= this.customerRepository.getcustomerByName(customerName);
			
			List<Account> accounts = this.accountRepository.findAccountByCustomer(customer.getId());

			model.addAttribute("accounts",accounts);
			
			return "normal/account_detail";
		}
		
		@GetMapping("/openNewAccount")
		public String openNewAccount(Model model){
			model.addAttribute("title","Account Details - KDFC Bank");

			model.addAttribute("account",new Account());
			
			return "normal/newAccount_form";
		}
		
		@PostMapping("/process-account")
		public String processAccount(@ModelAttribute Account account,Principal principal, Model model) {
			System.out.println("Data :"+account);
			String customerName = principal.getName();
		
 		    Customer customer = customerRepository.getcustomerByName(customerName);
			
 		    account.setCustomer(customer);
 		    customer.getAccount().add(account);
			this.customerRepository.save(customer);
			model.addAttribute("account",new Account());
		
			return "normal/newAccount_form";
		}
		
	
		//Nominee
		@GetMapping("/newNominee")
		public String newNomineeForm(Model model,Principal principal){
			model.addAttribute("title","Nominee Details - KDFC Bank");
			String customerName = principal.getName();
			Customer customer= this.customerRepository.getcustomerByName(customerName);
			
			List<Nominee> nominees = this.nomineeRepository.findAccountByCustomer(customer.getId());

			model.addAttribute("nominees",nominees);
			
			return "normal/nominee_detail";
		}
		
		@GetMapping("/addNewNominee")
		public String addNewNominee(Model model){
			model.addAttribute("title","Nominee Details - KDFC Bank");

			model.addAttribute("nominee",new Nominee());
			
			return "normal/newNominee_form";
		}
		
		//Nominee Updation transaction
		@PostMapping("/process-nominee")
		public String processNominee(@ModelAttribute Nominee nominee,Principal principal, Model model) {
			System.out.println("Data :"+nominee);
			String customerName = principal.getName();
		
 		    Customer customer = customerRepository.getcustomerByName(customerName);
			
 		    nominee.setCustomer(customer);
 		    customer.getNominee().add(nominee);
			this.customerRepository.save(customer);
			model.addAttribute("nominee",new Nominee());
		
			return "redirect:/customer/newNominee";
		}
		
		
		@PostMapping("/nomineeUpdate/{nId}")
		public String nomineeUpdate(@PathVariable("nId")Integer nId, Model model,HttpSession session) {
			model.addAttribute("title","Nominee Update - KDFC Bank");
			
			Nominee nominee = this.nomineeRepository.findById(nId).get();
			
			model.addAttribute("nominee",nominee);
			session.setAttribute("message", new Message("Updated Sucessfully !","success"));
			return "normal/nominee";
		}
		
		
//		//Nominee Updation transaction
//		@PostMapping("/process-updation")
//		public String processUpdation(Principal principal,HttpSession session,@ModelAttribute Nominee nominee) {
//			
//			try {
//
//				String customerName = principal.getName();
//				
//				Customer customer = this.customerRepository.getcustomerByName(customerName);
//			
//				//Nominee nominees = nomineeRepository.findById(nominee.getnId()).get();
//				
//					
//					nominee.setCustomer(customer);
//					//this.nomineeRepository.save(nominees);
//					this.customerRepository.save(customer);
//					
//					System.out.println("Added to database");
////					System.out.println("transaction :"+transaction);
//				session.setAttribute("message", new Message("Successful", "success"));
//				
//			} catch (Exception e) {
//				System.out.println("ERROR "+e.getMessage());
//				e.printStackTrace();
//				session.setAttribute("message", new Message("Failed", "danger"));
//
//			}
//			return "normal/nominee_detail";
//		}
		
		//Delete nominee
		@GetMapping("/nomineeDelete/{nId}")
		public String nomineeDelete(@PathVariable("nId")Integer nId, Model model, HttpSession session) {
			model.addAttribute("title","Nominee Delete - KDFC Bank");
			
			Nominee nominee = this.nomineeRepository.findById(nId).get();
			
			nominee.setCustomer(null);
			this.nomineeRepository.delete(nominee);
			session.setAttribute("message", new Message("Deleted Sucessfully !","success"));
			
			//model.addAttribute("nominee",nominee);
			return "redirect:/customer/newNominee";
		}
		
		
		//Money Transfer
		@GetMapping("/moneyTransfer")
		public String moneyTransfer(Model model,Principal principal,MoneyTransfer moneyTransfer){
			model.addAttribute("title","Money Transfer - KDFC Bank");
//			
//			 int accNo = moneyTransfer.getfAccNo();
			List<Account> account = this.accountRepository.findAll();
			model.addAttribute("account",account);	
			
			return "normal/moneyTransfer_form";
		}
		
		
		//processing
		@PostMapping("/process-MoneyTransfer")
		public String processMoneyTransfer( Transaction transaction,
				Principal principal,HttpSession session, Account account,@ModelAttribute MoneyTransfer moneyTransfer) {
			
			try {
					System.err.println("money...transfer :"+moneyTransfer);
					transaction.setAmt(moneyTransfer.getmAmt());
				String customerName = principal.getName();
				
				Customer customer = customerRepository.getcustomerByName(customerName);
				 int accNo = moneyTransfer.getfAccNo();
				Account account2 = this.accountRepository.findById(accNo).get();
				
				if(account2.getaBalance()>moneyTransfer.getmAmt()) {
					transaction.setDate(new Date(System.currentTimeMillis()));
					transaction.setTransac("MoneyTransfer");
					
					account2.setaBalance(account2.getaBalance()-moneyTransfer.getmAmt());
					transaction.settBalance(account2.getaBalance());
					
//					customer.setBalance(customer.getBalance()-transaction.getAmt());
//					transaction.settBalance(customer.getBalance());
					customer.getTransaction().add(transaction);
					
					transaction.setCustomer(customer);
					//moneyTransfer.setCustomer(customer);
					account.setCustomer(customer);
					this.customerRepository.save(customer);
					
					this.accountRepository.save(account2);
					
					this.moneyTransferRepository.save(moneyTransfer);
					System.out.println("Added to database");
//					System.out.println("transaction :"+transaction);
				session.setAttribute("message", new Message("Successful", "success"));
				}else {
					throw new Exception();
				}
				
			} catch (Exception e) {
				System.out.println("ERROR "+e.getMessage());
				e.printStackTrace();
				session.setAttribute("message", new Message("Account Not Found", "danger"));

			}
			return "normal/customer_dashboard";
		}
		
		
		//FD
		

		//
		@GetMapping("/fdDetails")
		public String fdDetail(Model model,Principal principal){
			model.addAttribute("title","Nominee Details - KDFC Bank");
			String customerName = principal.getName();
			Customer customer= this.customerRepository.getcustomerByName(customerName);
			List<Account> account = this.accountRepository.findAll();
			model.addAttribute("account",account);	
			//List<FixedDeposit> fixedDeposits = this.fdRepository.findAccountByCustomer(customer.getId());
			List<FixedDeposit> fixedDeposits = this.fdRepository.findAll();

			model.addAttribute("fixedDeposits",fixedDeposits);
			
			return "normal/fd_details";
		}
		
		@GetMapping("/addNewFD")
		public String addNewFD(Model model){
			model.addAttribute("title","FD Details - KDFC Bank");

			model.addAttribute("fixedDeposit",new FixedDeposit());
			
			return "normal/newFD_form";
		}
		
		//processing
				@PostMapping("/process-fd")
				public String processfd( Transaction transaction,
						Principal principal,HttpSession session, Account account,
						@ModelAttribute FixedDeposit fixedDeposit) {
					
				try {
						System.err.println("money...transfer :"+fixedDeposit);
						transaction.setAmt(fixedDeposit.getFdAmt());
						String customerName = principal.getName();
						
						Customer customer = customerRepository.getcustomerByName(customerName);
						 int accNo = fixedDeposit.getAccNo();
						 fixedDeposit.setRate(5);
						 
						 fixedDeposit.setmAmt(fixedDeposit.getFdAmt()*fixedDeposit.getDuration()*fixedDeposit.getRate()/100+fixedDeposit.getFdAmt());
						 fixedDeposit.setInvestDate(LocalDate.now());
						 fixedDeposit.setmDate(fixedDeposit.getInvestDate().plusMonths(fixedDeposit.getDuration()));
						 
						 
						Account account2 = this.accountRepository.findById(accNo).get();
						
						if(account2.getaBalance()>fixedDeposit.getFdAmt()) {
							transaction.setDate(new Date(System.currentTimeMillis()));
							transaction.setTransac("FD");
							
							account2.setaBalance(account2.getaBalance()-fixedDeposit.getFdAmt());
							transaction.settBalance(account2.getaBalance());
							
//							customer.setBalance(customer.getBalance()-transaction.getAmt());
//							transaction.settBalance(customer.getBalance());
							customer.getTransaction().add(transaction);
							customer.getFixedDeposite().add(fixedDeposit);
							account.getFixedDeposite().add(fixedDeposit);
							
							transaction.setCustomer(customer);
							//moneyTransfer.setCustomer(customer);
							account.setCustomer(customer);
						
						//	fixedDeposit.setAccount(account);
							fixedDeposit.setCustomer(customer);
							
							this.customerRepository.save(customer);
							
							this.accountRepository.save(account2);
							
							System.out.println("Added to database");
//							System.out.println("transaction :"+transaction);
						session.setAttribute("message", new Message("Successful", "success"));
						}else {
							throw new Exception();
						}
						
					} catch (Exception e) {
						System.out.println("ERROR "+e.getMessage());
						e.printStackTrace();
						session.setAttribute("message", new Message("Account Not Found", "danger"));

					}
					return "normal/fd_details";
				}
				
				
				
				//UPI 
				
				@GetMapping("/newUPI")
				public String newUPI(Model model,Principal principal){
					model.addAttribute("title","Account Details - KDFC Bank");
					String customerName = principal.getName();
					Customer customer= this.customerRepository.getcustomerByName(customerName);
					
					List<UPI> upi = this.upiRepository.findAccountByCustomer(customer.getId());

					model.addAttribute("upi",upi);
					
					return "normal/upi_detail";
				}
				
				
				@GetMapping("/openNewUPI")
				public String openNewUPI(Model model){
					model.addAttribute("title","UPI Details - KDFC Bank");

					model.addAttribute("upi",new UPI());
					
					return "normal/newUPI_form";
				}
				
				@PostMapping("/process-upi")
				public String processUpi(@ModelAttribute UPI upi,Principal principal, Model model) {
					System.out.println("Data :"+upi);
					String customerName = principal.getName();
				
		 		    Customer customer = customerRepository.getcustomerByName(customerName);
		 		   customer.getUPI().add(upi);
		 		    upi.setCustomer(customer);
		 		    
					this.customerRepository.save(customer);
					this.upiRepository.save(upi);
					model.addAttribute("upi",new UPI());
				
					return "normal/newUPI_form";
				}
				
				//UPI transaction
				@PostMapping("/process-upiTransaction")
				public String processUpiTransaction( Transaction transaction,
						Principal principal,HttpSession session,@ModelAttribute Account account,  UPI upi) {
					
					try {

						String customerName = principal.getName();
						
						Customer customer = customerRepository.getcustomerByName(customerName);
					
						Account account2 = accountRepository.findById(account.getAccNo()).get();
						
						if(account2.getaBalance()>upi.getAmt()) {
							transaction.setDate(new Date(System.currentTimeMillis()));
							transaction.setTransac("UPI Transfer");
							
							account2.setaBalance(account2.getaBalance()-upi.getAmt());
							transaction.settBalance(account2.getaBalance());
							
//							customer.setBalance(customer.getBalance()-transaction.getAmt());
//							transaction.settBalance(customer.getBalance());
							customer.getTransaction().add(transaction);
							transaction.setCustomer(customer);
							account.setCustomer(customer);
							upi.setCustomer(customer);
							this.customerRepository.save(customer);
							this.upiRepository.save(upi);
							this.accountRepository.save(account2);
							
							System.out.println("Added to database");
//							System.out.println("transaction :"+transaction);
						session.setAttribute("message", new Message("Successful", "success"));
						}else {
							throw new Exception();
						}
						
					} catch (Exception e) {
						System.out.println("ERROR "+e.getMessage());
						e.printStackTrace();
						session.setAttribute("message", new Message("Failed", "danger"));

					}
					return "redirect:/customer/newAccount";
				}
				
		
}
