package com.abhi.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abhi.entity.Transaction;
@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	//pagination
	@Query("from Transaction as c where c.customer.id= :customerID")
	// currentPage-page
	//contact per page - 5
	public Page<Transaction> findTransactionByCustomer(@Param("customerID") int customer_id,Pageable perPageable);
	
}
