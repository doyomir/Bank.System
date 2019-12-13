package com.Bank.System.service.validation;

import com.Bank.System.model.Account;
import com.Bank.System.model.Customer;
import com.Bank.System.model.enums.TransactionType;
import com.Bank.System.model.operation.Transaction;
import com.Bank.System.repository.AccountRepository;
import com.Bank.System.repository.CardRepository;
import com.Bank.System.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class TransactionValidation {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;

    @Autowired
    public TransactionValidation(AccountRepository accountRepository, CustomerRepository customerRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.cardRepository = cardRepository;
    }

    public void validateCustomer (Transaction transaction){
        Optional<Customer> customer = customerRepository.findById(transaction.getCustomer().getId());
        if(!customer.isPresent()){
            throw new RuntimeException("Invalid Customer");
        }
    }

    public void validateAccount (Transaction transaction){
        Optional<Account> account = accountRepository.findById(transaction.getAccount().getId());
        if(!account.isPresent()){
            throw new RuntimeException("Invalid Account");
        }
    }

    public void validateRecipientCustomer (Transaction transaction){
        if(transaction.getTransactionType().equals(TransactionType.CUSTOMER_TRANSACTION)){
            Optional<Customer> recipientCustomer = customerRepository.findById(transaction.getRecipientCustomer().getId());
            if(!recipientCustomer.isPresent()){
                throw new RuntimeException("Invalid Recipient Customer");
            }
        }
    }

    public void validateRecipientAccount (Transaction transaction){
        if(transaction.getTransactionType().equals(TransactionType.CUSTOMER_TRANSACTION)){
            Optional<Account> recipientAccount = accountRepository.findById(transaction.getRecipientAccount().getId());
            if(!recipientAccount.isPresent()){
                throw new RuntimeException("Invalid Recipient Account");
            }
        }

    }

    public void validateCardNumber (Transaction transaction){
        String cardNumber = cardRepository.getCard(transaction.getCardId().getId()).getCardNumber();
        if (!transaction.getCardNumber().equals(cardNumber)){
            throw new RuntimeException("Invalid card number");
        }

    }

    public void validateCardPin (Transaction transaction){
        Integer cardPin = cardRepository.getCard(transaction.getCardId().getId()).getCardPinNumber();
        if (!transaction.getCardPin().equals(cardPin) ){
            throw new RuntimeException("Wrong PIN number");
        }

    }

    public void validateCardExpirationDate (Transaction transaction){
        LocalDate cardExpirationDate = cardRepository.getCard(transaction.getCardId().getId()).getCardExpirationDate();

        if (cardExpirationDate.isBefore(LocalDate.now())){
            throw new RuntimeException("Your card has expired");
        }

    }

    public void validateCardCvvNumber (Transaction transaction){
        Integer cardCvvNumber = cardRepository.getCard(transaction.getCardId().getId()).getCardCvvNumber();
        if (!transaction.getCardCvvNumber().equals(cardCvvNumber)){
            throw new RuntimeException("Wrong CVV number");
        }

    }


}
