package com.Bank.System.model;


import com.Bank.System.model.enums.AccountType;
import com.Bank.System.model.enums.CurrencyType;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Where(clause = "is_deleted = false")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_gen", initialValue = 1,allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @NotNull
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @NotNull
    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "is_deleted")
    boolean isDeleted = false;

    public Integer getId() {
        return id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
