package com.abhi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	private int accNo;
	
	@NotBlank(message="Account Type field is required")
	private String accTyp;
	


	private int balance;
	
//	private String address;
//	private String city;
//	private int pin;
//	private String City;
	
	
	public Customer(int id, String name, Date dob, String gender, String email, String password, String role,int accNo, String accTyp, int balance) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.role = role;
		this.accNo = accNo;
		this.accTyp = accTyp;
		this.balance = balance;
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
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getAccTyp() {
		return accTyp;
	}
	public void setAccTyp(String accTyp) {
		this.accTyp = accTyp;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", accNo=" + accNo + ", accTyp=" + accTyp + ", balance="
				+ balance + "]";
	}
	

	


	
	
	
	
}
