package com.Bank.System.repository;

import com.Bank.System.model.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query("FROM Card Where id = :id")
    public Card getCard(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Card SET isDeleted = true WHERE id =:id")
    void deactivateCard(@Param("id") Integer id);


    @Query("SELECT c FROM Card c WHERE customer.id =:customerId")
    Optional<List<Card>> getCardByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT c FROM Card c WHERE account.id =:accountId")
    Optional<List<Card>> getCardByAccountId(@Param("accountId") Integer accountId);



}
