package com.abhi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UPI {
	@Id
	private int accNo;

	private String vaddress;
	private String pvaddress;
	private String pName;
	private String description;
	private double amt;

	@ManyToOne
	private Customer customer;

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getVaddress() {
		return vaddress;
	}

	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}

	public String getPvaddress() {
		return pvaddress;
	}

	public void setPvaddress(String pvaddress) {
		this.pvaddress = pvaddress;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public UPI(int accNo, String vaddress, String pvaddress, String pName, String description, double amt,
			Customer customer) {
		super();
		this.accNo = accNo;
		this.vaddress = vaddress;
		this.pvaddress = pvaddress;
		this.pName = pName;
		this.description = description;
		this.amt = amt;
		this.customer = customer;
	}

	public UPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UPI [accNo=" + accNo + ", vaddress=" + vaddress + ", pvaddress=" + pvaddress + ", pName=" + pName
				+ ", description=" + description + ", amt=" + amt + ", customer=" + customer + "]";
	}


}
