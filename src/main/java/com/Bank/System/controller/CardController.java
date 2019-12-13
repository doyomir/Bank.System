package com.Bank.System.controller;

import com.Bank.System.model.Card;
import com.Bank.System.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/card")
public class CardController {
    private  CardService cardService;

    @Autowired
    public CardController( CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void createOrUpdateCard(@RequestBody Card card){
        cardService.createOrUpdateCard(card);
    }

    @GetMapping("/{cardId}")
    public Card getCard(@PathVariable("/{cardId}") Integer cardId){
        return cardService.getCard(cardId);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable("/{cardId}") Integer cardId){
        cardService.deleteCard(cardId);
    }

}
