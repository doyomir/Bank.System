package com.Bank.System.model;

import com.Bank.System.model.enums.CardType;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "card")
@Where(clause = "is_deleted = false")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_gen")
    @SequenceGenerator(name = "card_gen", sequenceName = "card_gen", initialValue = 1,allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @NotEmpty
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull
    @Column(name = "card_pin_number")
    private Integer cardPinNumber;

    @NotNull
    @Column(name = "card_expiration_date")
    private LocalDate cardExpirationDate;

    @NotNull
    @Column(name = "card_cvv_number")
    private Integer cardCvvNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "is_deleted")
    boolean isDeleted = false;

    public Integer getId() {
        return id;
    }


    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardPinNumber() {
        return cardPinNumber;
    }

    public void setCardPinNumber(Integer cardPinNumber) {
        this.cardPinNumber = cardPinNumber;
    }

    public LocalDate getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(LocalDate cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public Integer getCardCvvNumber() {
        return cardCvvNumber;
    }

    public void setCardCvvNumber(Integer cardCvvNumber) {
        this.cardCvvNumber = cardCvvNumber;
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
}
