package com.abhi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int tId;
	private double amt;
	private String transac;
	private double tBalance;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")

	private Date date;
	@ManyToOne
	private Customer customer;
	
	
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getTransac() {
		return transac;
	}
	public void setTransac(String transac) {
		this.transac = transac;
	}
	
	public double gettBalance() {
		return tBalance;
	}
	public void settBalance(double tBalance) {
		this.tBalance = tBalance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int tId, double amt, String transac,double tBalance, Date date, Customer customer) {
		super();
		this.tId = tId;
		this.amt = amt;
		this.transac = transac;
		this.date = date;
		this.customer = customer;
		this.tBalance=tBalance;
	}
	@Override
	public String toString() {
		return "Transaction [tId=" + tId + ", amt=" + amt + ", transac=" + transac + ", tBalance=" + tBalance
				+ ", date=" + date + ", customer=" + customer + "]";
	}

	
	
}
