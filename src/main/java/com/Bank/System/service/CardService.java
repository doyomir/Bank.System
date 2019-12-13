package com.Bank.System.service;

import com.Bank.System.model.Card;
import com.Bank.System.repository.AccountRepository;
import com.Bank.System.repository.CardRepository;
import com.Bank.System.service.validation.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final CardValidation cardValidation;

    @Autowired
    public CardService(AccountRepository accountRepository, CardRepository cardRepository, CardValidation cardValidation) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.cardValidation = cardValidation;
    }

    public void createOrUpdateCard (Card card){
        if(null == card.getId()){
            validateAndCreateCard(card);
            return;
        }
        updateCard(card);

    }

    public Card getCard(Integer cardId){
        Optional<Card>  card = cardRepository.findById(cardId);

        if(!card.isPresent()){
            throw new RuntimeException("Card not found!") ;
        }
        return card.get();
    }

    public void deleteCard (Integer cardId){
        cardRepository.deactivateCard(cardId);
    }

    private void updateCard(Card card) {
        cardRepository.save(card);
    }

    private void validateAndCreateCard(Card card) {
        cardValidation.cardValidation(card);
        cardRepository.save(card);
    }
}
