package com.abhi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FixedDeposit {
	@Id
	private int accNo;
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int fdId;
	
	private double fdAmt;
	private int duration;
	private double mAmt;
	

	@ManyToOne
	private Customer customer;


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


	public FixedDeposit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FixedDeposit(int fdId, int accNo, double fdAmt, int duration,double mAmt, Customer customer) {
		super();
		this.fdId = fdId;
		this.accNo = accNo;
		this.fdAmt = fdAmt;
		this.duration = duration;
		this.mAmt=mAmt;
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "FixedDeposite [fdId=" + fdId + ", accNo=" + accNo + ", fdAmt=" + fdAmt + ", duration=" + duration
				+ ", mAmt=" + mAmt + ", customer=" + customer + "]";
	}


	
}
