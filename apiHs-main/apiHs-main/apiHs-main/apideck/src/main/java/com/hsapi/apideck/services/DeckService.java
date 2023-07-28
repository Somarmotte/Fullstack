package com.hsapi.apideck.services;
import com.hsapi.apideck.controller.CarteController;
import com.hsapi.apideck.exception.CarteNotFoundException;
import com.hsapi.apideck.exception.DeckFullException;
import com.hsapi.apideck.exception.DeckNotFoundException;
import com.hsapi.apideck.exception.TooManySameCardException;
import com.hsapi.apideck.model.Carte;
import com.hsapi.apideck.model.Deck;
import com.hsapi.apideck.controller.DeckController;
import com.hsapi.apideck.repo.CarteRepository;
import com.hsapi.apideck.repo.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public class DeckService {

    public static DeckRepository deckRepository;

    public static CarteRepository carteRepository;



    DeckService(DeckRepository repositoryDeck, CarteRepository repositoryCarte){
        this.deckRepository = repositoryDeck;
        this.carteRepository = repositoryCarte;
    }
     public Deck addCardToList(Integer idDeck, Integer idCarte){
        Deck deck = deckRepository.findById(idDeck)
               .orElseThrow(() -> new DeckNotFoundException(idDeck));
        Carte carte = carteRepository.findById(idCarte)
                .orElseThrow(() -> new CarteNotFoundException(idCarte));
         if (deck.getLikedCarte().size() >= 30){
             throw new DeckFullException(idDeck);
         }

         long countWithoutLegendary = deck.getLikedCarte().stream()
                 .filter(item -> item.getIdCarte() == carte.getIdCarte())
                 .count();


         if (countWithoutLegendary >= 2){
            throw  new TooManySameCardException(idDeck);
         }

         if (carte.getRarity() == "Legendary" && carte.getIdCarte().describeConstable().isPresent())
         {
             throw  new TooManySameCardException(idDeck);
         }

         if (deck.getPlayerClass().equals(carte.getPlayerClass()) ||
                 carte.getPlayerClass().equals("Neutral")){
             deck.addCarte(carte);
             deckRepository.save(deck);
             carteRepository.save(carte);
         }
         else {
             throw new CarteNotFoundException(idCarte);
         }

        return deck;
      }

    public static Deck useRepoDeck(Deck deck1){

        return deckRepository.save(deck1);

    }

    public Deck createDeck(String playerClass, String deckName){
        Deck deck = new Deck(playerClass, deckName);

        return deckRepository.save(deck);
    }


    public int checkNbrCardInDeck(Integer idDeck){

        Deck deck = deckRepository.findById(idDeck)
                .orElseThrow(() -> new DeckNotFoundException(idDeck));

        return deck.getLikedCarte().size();
    }



}
