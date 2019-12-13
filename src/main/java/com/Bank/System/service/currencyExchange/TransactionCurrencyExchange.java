package com.Bank.System.service.currencyExchange;

import com.Bank.System.model.operation.Transaction;
import com.Bank.System.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionCurrencyExchange {
    private  final AccountRepository accountRepository;

    public TransactionCurrencyExchange(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BigDecimal exchangeMultiplyer(Transaction transaction) {
        BigDecimal rate = null;
       switch (accountRepository.getAccount(transaction.getAccount().getId()).getCurrencyType()) {
           case LEVA:

                switch (accountRepository.getAccount(transaction.getRecipientAccount().getId()).getCurrencyType()) {
                    case LEVA:
                        return rate = BigDecimal.ONE;
                    case EURO:
                        return rate = new BigDecimal(1/1.955);
                    case DOLLAR:
                        return rate = new BigDecimal(1 / 1.7687);

                }
            case EURO:
                switch (accountRepository.getAccount(transaction.getRecipientAccount().getId()).getCurrencyType()) {
                    case LEVA:
                        return rate = new  BigDecimal(1.955);
                    case EURO:
                        return rate = BigDecimal.ONE;
                    case DOLLAR:
                        return rate = new BigDecimal(1.1);
                }
            case DOLLAR:
                switch (accountRepository.getAccount(transaction.getRecipientAccount().getId()).getCurrencyType()) {
                    case LEVA:
                        return rate = new BigDecimal(1.7687);
                    case EURO:
                        return rate = new BigDecimal(0.91);
                    case DOLLAR:
                        return rate = BigDecimal.ONE;
                }

       }
        return rate;
    }
}
