package com.abhi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MoneyTransfer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;
	private int fAccNo;
	private int tAccNo;
	private double mAmt;
	
	@ManyToOne
	private Customer customer;

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public int getfAccNo() {
		return fAccNo;
	}

	public void setfAccNo(int fAccNo) {
		this.fAccNo = fAccNo;
	}

	public int gettAccNo() {
		return tAccNo;
	}

	public void settAccNo(int tAccNo) {
		this.tAccNo = tAccNo;
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

	public MoneyTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MoneyTransfer(int mId, int fAccNo, int tAccNo, double mAmt, Customer customer) {
		super();
		this.mId = mId;
		this.fAccNo = fAccNo;
		this.tAccNo = tAccNo;
		this.mAmt = mAmt;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "MoneyTransfer [mId=" + mId + ", fAccNo=" + fAccNo + ", tAccNo=" + tAccNo + ", mAmt=" + mAmt
				+ ", customer=" + customer + "]";
	}
	
	
}
