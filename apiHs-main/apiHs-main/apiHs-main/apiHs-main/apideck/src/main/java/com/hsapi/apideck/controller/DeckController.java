package com.hsapi.apideck.controller;

import java.util.List;

import com.hsapi.apideck.exception.CarteNotFoundException;
import com.hsapi.apideck.exception.DeckNotFoundException;
import com.hsapi.apideck.model.Deck;
import com.hsapi.apideck.repo.DeckRepository;
import com.hsapi.apideck.repo.CarteRepository;
import com.hsapi.apideck.services.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mysql.jdbc.*;


@RestController
public class DeckController {

    private static DeckRepository deckRepository;

    private static DeckService deckService;

    private static CarteRepository carteRepository;

    DeckController(DeckRepository repoDeck, DeckService service, CarteRepository repoCarte) {

        this.deckRepository = repoDeck;
        this.deckService = service;
        this.carteRepository = repoCarte;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/decks")
    List<Deck> all() {
        return (List<Deck>) deckRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/decks")
    Deck newDeck(@RequestBody Deck data) {

        return deckService.createDeck(data.getPlayerClass(), data.getDeckName());
    }

    // Single item

    @GetMapping("/decks/{id}")
    Deck one(@PathVariable Integer id) {

        return deckRepository.findById(id)
                .orElseThrow(() -> new DeckNotFoundException(id));
    }

    @PutMapping("/decks/{id}")
    Deck replaceDeck(@RequestBody Deck newDeck, @PathVariable Integer id) {

        return deckRepository.findById(id)
                .map(deck -> {
                    deck.setDeckName(newDeck.getDeckName());
                    return deckRepository.save(deck);
                })
                .orElseGet(() -> {
                    newDeck.setDeckId(id);
                    return deckRepository.save(newDeck);
                });
    }

    @DeleteMapping("/decks/{id}")
    void deleteDeck(@PathVariable Integer id) {
        deckRepository.deleteById(id);
    }

    @PostMapping("/decks/{idDeck}/cartes/{idCarte}")
    ResponseEntity addCarteToDeck(@PathVariable Integer idCarte,@PathVariable Integer idDeck) {

            try {
                return new ResponseEntity(deckService.addCardToList(idDeck, idCarte), HttpStatus.OK) ;
            }
            catch(CarteNotFoundException | DeckNotFoundException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }

          //  catch(DeckFullException | IncompatibleClassException e){
           //     return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
          //  }


    }

    @DeleteMapping("/decks/{idDeck}/cartes/{idCard}")
    ResponseEntity deleteCardInDeck(@PathVariable Integer idDeck, @PathVariable Integer idCard) {

        try {
            return new ResponseEntity(deckService.deleteCardToDeck(idDeck, idCard), HttpStatus.OK) ;
        }
        catch(CarteNotFoundException | DeckNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
