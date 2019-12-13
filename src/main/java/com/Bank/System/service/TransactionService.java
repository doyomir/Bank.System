package com.Bank.System.service;

import com.Bank.System.model.operation.Transaction;
import com.Bank.System.repository.AccountRepository;
import com.Bank.System.repository.CustomerRepository;
import com.Bank.System.repository.TransactionRepository;
import com.Bank.System.service.currencyExchange.TransactionCurrencyExchange;
import com.Bank.System.service.validation.TransactionValidation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionValidation transactionValidation;
    private final TransactionCurrencyExchange transactionCurrencyExchange;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, CustomerRepository customerRepository, TransactionValidation transactionValidation, TransactionCurrencyExchange transactionCurrencyExchange) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionValidation = transactionValidation;
        this.transactionCurrencyExchange = transactionCurrencyExchange;
    }

    public void createTransaction(Transaction transaction) {

        switch (transaction.getTransactionType()) {
            case WITHDRAW:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                makeWithdraw(transaction);
                break;

            case DEPOSIT:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                makeDeposit(transaction);
                break;

            case CUSTOMER_TRANSACTION:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                transactionValidation.validateRecipientCustomer(transaction);
                transactionValidation.validateRecipientAccount(transaction);
                makeCustomerTransaction(transaction);
                break;

            case CARD_WITHDRAW:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                transactionValidation.validateCardNumber(transaction);
                transactionValidation.validateCardPin(transaction);
                transactionValidation.validateCardExpirationDate(transaction);
                makeWithdraw(transaction);
                break;

            case CARD_DEPOSIT:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                transactionValidation.validateCardNumber(transaction);
                transactionValidation.validateCardPin(transaction);
                transactionValidation.validateCardExpirationDate(transaction);
                makeDeposit(transaction);
                break;

            case CARD_ONLINE_PAYMENT:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                transactionValidation.validateCardNumber(transaction);
                transactionValidation.validateCardCvvNumber(transaction);
                transactionValidation.validateCardExpirationDate(transaction);
                makeWithdraw(transaction);
                break;

            case CARD_NFC_PAYMENT:
                transactionValidation.validateCustomer(transaction);
                transactionValidation.validateAccount(transaction);
                transactionValidation.validateCardNumber(transaction);
                if (transaction.getAmount().intValue() >= 50) transactionValidation.validateCardPin(transaction);
                makeWithdraw(transaction);
                break;

        }

    }


    private void makeWithdraw(Transaction transaction) {
        BigDecimal accountBalance = accountRepository.getAccount(transaction.getAccount().getId()).getBalance();

        if (accountBalance.compareTo(transaction.getAmount()) >= 0) {

            accountRepository.updateBalance(accountBalance.subtract(transaction.getAmount()), transaction.getAccount().getId());

            transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("You don't have enough money in your account! ");
        }
    }

    private void makeDeposit(Transaction transaction) {
        BigDecimal accountBalance = accountRepository.getAccount(transaction.getAccount().getId()).getBalance();

        accountRepository.updateBalance(accountBalance.add(transaction.getAmount()), transaction.getAccount().getId());

        transactionRepository.save(transaction);

    }

    private void makeCustomerTransaction(Transaction transaction) {
        BigDecimal accountBalanceSender = accountRepository.getAccount(transaction.getAccount().getId()).getBalance();
        BigDecimal accountBalanceRecipient = accountRepository.getAccount(transaction.getRecipientAccount().getId()).getBalance();

        if (accountBalanceSender.compareTo(transaction.getAmount()) >= 0) {

            accountRepository.updateBalance(accountBalanceSender.subtract(transaction.getAmount()), transaction.getAccount().getId());

            System.out.println("doyomir" + "transactionCurrencyExchange.exchangeMultiplyer(transaction)");

            accountRepository.updateBalance(accountBalanceRecipient.add(transaction.getAmount().multiply(transactionCurrencyExchange.exchangeMultiplyer(transaction))), transaction.getRecipientAccount().getId());

            transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("You don't have enough money in your account! ");
        }

    }
}
