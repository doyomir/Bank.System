package com.Bank.System.service;

import com.Bank.System.model.Account;
import com.Bank.System.model.Card;
import com.Bank.System.repository.AccountRepository;
import com.Bank.System.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public void createOrUpdateAccount(Account account) {
        accountRepository.save(account);
    }

    public Account getAccount(Integer accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (!account.isPresent()) {
            throw new RuntimeException("Account not found!");
        }
        return account.get();
    }

    public void deleteAccount(Integer accountId) {
        Optional<List<Card>> cards = cardRepository.getCardByAccountId(accountId);

        if (cards.isPresent()) {
            for (Card card : cards.get()) {
                Integer cardId = card.getId();
                cardRepository.deactivateCard(cardId);
            }
        }
        accountRepository.deactivateAccount(accountId);
    }
}