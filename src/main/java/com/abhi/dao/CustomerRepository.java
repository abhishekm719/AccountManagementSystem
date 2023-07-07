package com.abhi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

		@Query("select c from Customer c where c.email = :email")
		public Customer getcustomerByName(@Param("email")String email);
}
