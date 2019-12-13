package com.Bank.System.model.operation;

import com.Bank.System.model.Account;
import com.Bank.System.model.Card;
import com.Bank.System.model.Customer;
import com.Bank.System.model.enums.TransactionType;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_gen")
    @SequenceGenerator(name = "trans_gen", sequenceName = "trans_gen", initialValue = 1,allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "recipient_customer_id")
    private Customer recipientCustomer;

    @ManyToOne
    @JoinColumn(name = "recipient_account_id")
    private Account recipientAccount;


    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @CreatedDate
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Transient
    private Integer cardPin;

    @Transient
    private Integer cardCvvNumber;

    public Integer getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getRecipientCustomer() {
        return recipientCustomer;
    }

    public void setRecipientCustomer(Customer recipientCustomer) {
        this.recipientCustomer = recipientCustomer;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Card getCardId() {
        return cardId;
    }

    public void setCardId(Card cardId) {
        this.cardId = cardId;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardPin() {
        return cardPin;
    }

    public void setCardPin(Integer cardPin) {
        this.cardPin = cardPin;
    }

    public Integer getCardCvvNumber() {
        return cardCvvNumber;
    }

    public void setCardCvvNumber(Integer cardCvvNumber) {
        this.cardCvvNumber = cardCvvNumber;
    }
}
