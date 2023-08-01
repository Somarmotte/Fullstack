package com.hsapi.apideck.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.*;

@Entity
public class Deck {



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer deckId;

    @ManyToMany
    @JoinTable(
            name = "deck-like",
            joinColumns = @JoinColumn(name = "deck_id"))
    Set<Carte> likedCarte;


    @JsonProperty("deckName")
    private String deckName;

    private String playerClass;

    public Deck() {
    }

    public Deck(String playerClass, String deckName) {
        this.deckName = deckName;
        this.playerClass = playerClass;
    }

    public Deck(Set<Carte> likedCarte, String deckName, String playerClass) {
        this.likedCarte = likedCarte;
        this.deckName = deckName;
        this.playerClass = playerClass;
    }

    public Set<Carte> getLikedCarte() {
        return likedCarte;
    }

    public void setLikedCarte(Set<Carte> likedCarte) {
        this.likedCarte = likedCarte;
    }

    public String getPlayerClass() {
        return playerClass;
    }


    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }


    public Integer getDeckId() {
        return deckId;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void addCarte(Carte carte){
        this.likedCarte.add(carte);
        carte.getLikedDeck().add(this);
    }
    public void deleteCarte(Carte carte){
        this.likedCarte.remove(carte);
        carte.getLikedDeck().remove(this);
    }

}
