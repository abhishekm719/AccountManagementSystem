package com.abhi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@OneToMany(cascade=CascadeType.ALL,mappedBy="account")
	private List<FixedDeposit> FixedDeposite =new ArrayList<>();
	
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

	
	
	public List<FixedDeposit> getFixedDeposite() {
		return FixedDeposite;
	}

	public void setFixedDeposite(List<FixedDeposit> fixedDeposite) {
		FixedDeposite = fixedDeposite;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int aId, double aBalance, int accNo, String accType, Customer customer,
			List<FixedDeposit> fixedDeposite) {
		super();
		this.aId = aId;
		this.aBalance = aBalance;
		this.accNo = accNo;
		this.accType = accType;
		this.customer = customer;
		FixedDeposite = fixedDeposite;
	}

	@Override
	public String toString() {
		return "Account [aId=" + aId + ", aBalance=" + aBalance + ", accNo=" + accNo + ", accType=" + accType
				+ ", customer=" + customer + ", FixedDeposite=" + FixedDeposite + "]";
	}




}
