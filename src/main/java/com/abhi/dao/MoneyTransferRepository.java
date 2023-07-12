package com.abhi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhi.entity.Account;
import com.abhi.entity.MoneyTransfer;

public interface MoneyTransferRepository extends JpaRepository<MoneyTransfer, Integer>{

	@Query("from Account as c where c.customer.id= :customer_id")
	public List<MoneyTransfer> findAccountByCustomer(@Param("customer_id")int customer_id);
	
	


}
