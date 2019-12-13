package com.Bank.System.service;


import com.Bank.System.model.Account;
import com.Bank.System.model.Card;
import com.Bank.System.model.Customer;
import com.Bank.System.repository.AccountRepository;
import com.Bank.System.repository.CardRepository;
import com.Bank.System.repository.CustomerRepository;
import com.Bank.System.service.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final CustomerValidation customerValidation;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository, CardRepository cardRepository, CustomerValidation customerValidation) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.customerValidation = customerValidation;
    }


    public void createOrUpdateCustomer(Customer customer) {
        if (null == customer.getId()) {
            validateAndCreateCustomer(customer);
            return;
        }
        updateCustomer(customer);
    }

    public Customer getCustomer(Integer customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (!customer.isPresent()) {
            throw new RuntimeException("Customer not found!");
        }
        return customer.get();
    }

    private void validateAndCreateCustomer(Customer customer) {
        customerValidation.validateCustomer(customer);
        customerRepository.save(customer);

    }

    private void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public void deleteCustomer(Integer customerId) {
        Optional<List<Account>> accounts = accountRepository.getAccountByCustomerId(customerId);
        Optional<List<Card>> cards = cardRepository.getCardByCustomerId(customerId);

        if (cards.isPresent()) {
            for (Card card : cards.get()) {
                Integer cardId = card.getId();
                cardRepository.deactivateCard(cardId);
            }
        }

        if(accounts.isPresent()) {
            for (Account account : accounts.get()) {
                Integer id = account.getId();
                accountRepository.deactivateAccount(id);
            }
        }
        customerRepository.deactivateCustomer(customerId);

    }
}