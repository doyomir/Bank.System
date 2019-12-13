package com.Bank.System.repository;

        import com.Bank.System.model.Account;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;
        import javax.transaction.Transactional;
        import java.math.BigDecimal;
        import java.util.List;
        import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Account SET isDeleted = true WHERE id =:id")
    void deactivateAccount(@Param("id") Integer id);

    @Query("FROM Account Where id = :id")
    public Account getAccount(@Param("id") Integer id);

    @Query("SELECT a FROM Account a WHERE customer.id = :customerId")
    Optional<List<Account>> getAccountByCustomerId(@Param("customerId") Integer customerId);


    @Modifying
    @Transactional
    @Query("UPDATE   Account SET balance =:balance WHERE id =:id")
    void updateBalance(@Param("balance") BigDecimal balance, @Param("id") Integer id);
}
