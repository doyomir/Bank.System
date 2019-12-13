package com.Bank.System.service.validation;

import com.Bank.System.model.Card;
import com.Bank.System.model.enums.AccountType;
import com.Bank.System.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class CardValidation {
   private final AccountRepository accountRepository;

    public CardValidation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void cardValidation(Card card){
        if(  ((accountRepository.getAccount(card.getAccount().getId())).getType()) == AccountType.SAVINGS ){
            throw new RuntimeException("You have to select checking account to create a card") ;
        }

        validateCardNumber(card);
        validateCardPin(card);
        validateCardExpirationDate(card);
        validateCardCvvNumber(card);
    }

    private void validateCardNumber(Card card) {

        String CardNumber = card.getCardNumber();
        String patternName = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}";
        if(!CardNumber.matches(patternName)){
            throw new RuntimeException("Invalid Card number! Card number pattern must be (XXXX XXXX XXXX XXXX)");
        }

    }

    private void validateCardPin(Card card) {
        String pinNumber = card.getCardPinNumber().toString();
        String patternName = "\\d{4}";
        if(!pinNumber.matches(patternName)){
            throw new RuntimeException("Invalid Pin number! Card pin number pattern must be (XXXX)");
        }

    }

    private void validateCardExpirationDate(Card card) {
        String expirationDate = card.getCardExpirationDate().toString();
        String patternName = "^(19|20)\\d{2}[-](0?[1-9]|1[0-2])[-](0?[1-9]|[12]\\d|3[01])$";
        if(!expirationDate.matches(patternName)){
            throw new RuntimeException("Invalid date format! Date formatmust be (YYYY-MM-DD)");
        }

    }

    private void validateCardCvvNumber(Card card) {
        String cvvNumber = card.getCardCvvNumber().toString();
        String patternName = "\\d{3}";
        if(!cvvNumber.matches(patternName)){
            throw new RuntimeException("Invalid cvv number! Card cvv number pattern must be (XXX)");
        }

    }


}
