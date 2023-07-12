package com.abhi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhi.entity.FixedDeposit;

public interface FDRepository extends JpaRepository<FixedDeposit, Integer>{

	@Query("from FixedDeposit as c where c.customer.id= :customer_id")
	public List<FixedDeposit> findAccountByCustomer(@Param("customer_id")int customer_id);
	
	


}
