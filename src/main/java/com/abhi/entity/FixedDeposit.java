package com.abhi.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class FixedDeposit {
	@Id
	private int accNo;
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int fdId;
	
	private double fdAmt;
	private int duration;
	private double mAmt;
	private int rate;
	
	
	private LocalDate investDate;
	
	
	private LocalDate mDate;


	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Account account;
	
	
	public int getFdId() {
		return fdId;
	}


	public void setFdId(int fdId) {
		this.fdId = fdId;
	}


	public int getAccNo() {
		return accNo;
	}


	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}


	public double getFdAmt() {
		return fdAmt;
	}


	public void setFdAmt(double fdAmt) {
		this.fdAmt = fdAmt;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}

	

	public double getmAmt() {
		return mAmt;
	}


	public void setmAmt(double mAmt) {
		this.mAmt = mAmt;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}


	public LocalDate getInvestDate() {
		return investDate;
	}


	public void setInvestDate(LocalDate investDate) {
		this.investDate = investDate;
	}


	public LocalDate getmDate() {
		return mDate;
	}


	public void setmDate(LocalDate mDate) {
		this.mDate = mDate;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}



	public FixedDeposit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FixedDeposit(int accNo, int fdId, double fdAmt, int duration, double mAmt, int rate, LocalDate investDate,
			LocalDate mDate, Customer customer, Account account) {
		super();
		this.accNo = accNo;
		this.fdId = fdId;
		this.fdAmt = fdAmt;
		this.duration = duration;
		this.mAmt = mAmt;
		this.rate = rate;
		this.investDate = investDate;
		this.mDate = mDate;
		this.customer = customer;
		this.account = account;
	}


	@Override
	public String toString() {
		return "FixedDeposit [accNo=" + accNo + ", fdId=" + fdId + ", fdAmt=" + fdAmt + ", duration=" + duration
				+ ", mAmt=" + mAmt + ", rate=" + rate + ", investDate=" + investDate + ", mDate=" + mDate
				+ ", customer=" + customer + ", account=" + account + "]";
	}




	
}
