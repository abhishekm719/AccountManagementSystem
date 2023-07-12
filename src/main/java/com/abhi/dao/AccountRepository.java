package com.abhi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhi.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	@Query("from Account as c where c.customer.id= :customer_id")
	public List<Account> findAccountByCustomer(@Param("customer_id")int customer_id);
	
	


}
