package com.hsapi.apideck.services;
import com.hsapi.apideck.controller.CarteController;
import com.hsapi.apideck.exception.CarteNotFoundException;
import com.hsapi.apideck.exception.DeckNotFoundException;
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
             throw new Error("Deck complet");
         }

         if (deck.getPlayerClass().equals(carte.getPlayerClass()) ||
                 carte.getPlayerClass().equals("Neutral")){
             deck.addCarte(carte);
             deckRepository.save(deck);
             carteRepository.save(carte);
         }
         else {
             throw new CarteNotFoundException(1);
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

        //recuperer un deck depuis la db
        Deck deck = deckRepository.findById(idDeck)
                .orElseThrow(() -> new DeckNotFoundException(idDeck));

        return deck.getLikedCarte().size();//renvoyer la taille de la liste de carte du deck;
    }

    public Deck deleteCardToDeck(Integer idDeck, Integer idCard){

        Deck deck = deckRepository.findById(idDeck)
                .orElseThrow(() -> new DeckNotFoundException(idDeck));
        Carte carte = carteRepository.findById(idCard)
                .orElseThrow(() -> new CarteNotFoundException(idCard));

        deck.deleteCarte(carte);
        deckRepository.save(deck);
        carteRepository.save(carte);

        return deck;

    }

}
