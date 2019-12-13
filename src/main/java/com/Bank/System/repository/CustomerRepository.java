package com.Bank.System.repository;

import com.Bank.System.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("FROM Customer Where id = :id")
    public Customer getCustomer(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Customer SET isDeleted = true WHERE id =:id")
    void deactivateCustomer(@Param("id") Integer id);



}