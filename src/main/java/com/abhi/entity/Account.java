package com.abhi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account {
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int aId;
	private double aBalance;
	
	@Id
	private int accNo;
	private String accType;
	
	@ManyToOne
	private Customer customer;

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public double getaBalance() {
		return aBalance;
	}

	public void setaBalance(double aBalance) {
		this.aBalance = aBalance;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int aId, double aBalance, int accNo, String accType, Customer customer) {
		super();
		this.aId = aId;
		this.aBalance = aBalance;
		this.accNo = accNo;
		this.accType = accType;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [aId=" + aId + ", aBalance=" + aBalance + ", accNo=" + accNo + ", accType=" + accType
				+ ", customer=" + customer + "]";
	}
	


}
