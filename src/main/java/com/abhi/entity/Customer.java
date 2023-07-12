package com.abhi.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;



@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="Name field is required")
	@Size(min=2,max=20,message="min 2 to max 20 characters are allowed !")
	private String name;
	
	//@Past(message="Wrong Date of birth")
	private Date dob;
	
	@NotBlank(message="Gender field is required")
	private String gender;
	
	@Column(unique=true)
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	@NotBlank(message="Passsword field is required")
	private String password;
	
	private String role;

	//private int accNo;
	
	//@NotBlank(message="Account Type field is required")
	//private String accTyp;
	
	//private double balance;
	
//	@NotBlank(message="Nominee Type field is required")
//	private String nominee;
//
//	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	private List<Transaction> transaction=new ArrayList<>();

	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	private List<Account> account=new ArrayList<>();
	

	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	private List<Nominee> nominee=new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	private List<MoneyTransfer> moneyT =new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	private List<FixedDeposit> FixedDeposite =new ArrayList<>();
	
	
	public Customer(int id, String name, Date dob, String gender, String email, 
			String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.role = role;
//		this.accNo = accNo;
//		this.accTyp = accTyp;
//		this.balance = balance;
//		this.nominee=nominee;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
//	public int getAccNo() {
//		return accNo;
//	}
//	public void setAccNo(int accNo) {
//		this.accNo = accNo;
//	}
//	public String getAccTyp() {
//		return accTyp;
//	}
//	public void setAccTyp(String accTyp) {
//		this.accTyp = accTyp;
//	}
//	public double getBalance() {
//		return balance;
//	}
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
	
//	public String getNominee() {
//		return nominee;
//	}
//
//	public void setNominee(String nominee) {
//		this.nominee = nominee;
//	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public List<Nominee> getNominee() {
		return nominee;
	}

	public void setNominee(List<Nominee> nominee) {
		this.nominee = nominee;
	}
	
	

	public List<MoneyTransfer> getMoneyT() {
		return moneyT;
	}

	public void setMoneyT(List<MoneyTransfer> moneyT) {
		this.moneyT = moneyT;
	}
	
	

	public List<FixedDeposit> getFixedDeposite() {
		return FixedDeposite;
	}

	public void setFixedDeposite(List<FixedDeposit> fixedDeposite) {
		FixedDeposite = fixedDeposite;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", transaction=" + transaction + ", account=" + account
				+ ", nominee=" + nominee + ", moneyT=" + moneyT + ", FixedDeposite=" + FixedDeposite + "]";
	}

	

	
	

	
}
