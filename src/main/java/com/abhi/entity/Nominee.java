package com.abhi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Nominee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int nId;
	
	private String name;
	private String relation;
	private String address;
	

	@ManyToOne
	private Customer customer;


	public int getnId() {
		return nId;
	}


	public void setnId(int nId) {
		this.nId = nId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRelation() {
		return relation;
	}


	public void setRelation(String relation) {
		this.relation = relation;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Nominee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Nominee(int nId, String name, String relation, String address, Customer customer) {
		super();
		this.nId = nId;
		this.name = name;
		this.relation = relation;
		this.address = address;
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Nominee [nId=" + nId + ", name=" + name + ", relation=" + relation + ", address=" + address
				+ ", customer=" + customer + "]";
	}
	
}
