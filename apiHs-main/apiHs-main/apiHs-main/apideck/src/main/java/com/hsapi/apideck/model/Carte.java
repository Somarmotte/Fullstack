package com.hsapi.apideck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;


@Entity
public class Carte {

    @Id @GeneratedValue
    private Integer idCarte;



    @ManyToMany
    @JoinTable(
            name = "carte-like",
            joinColumns = @JoinColumn(name = "carte_id"))
    @JsonIgnore
        Set<Deck> likedDeck;


    private String nameCarte;
    private String typeCarte;
    private String playerClass;

    private String img;
    private String cardSet;

    private String rarity;

    public Carte() {

    }

    public String getType() {
        return typeCarte;
    }

    public void setType(String type) {
        this.typeCarte = typeCarte;
    }


    public Set<Deck> getLikedDeck() {
        return likedDeck;
    }

    public void setLikedDeck(Set<Deck> likedDeck) {
        this.likedDeck = likedDeck;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }


    public Carte(JSONObject jsoncard) {
        this.nameCarte = jsoncard.getString("name");
        try {
            this.typeCarte = jsoncard.getString("type");
        }
        catch (JSONException e){
            this.typeCarte = "";
        }
        this.playerClass = jsoncard.getString("playerClass");
        try {

            this.img = jsoncard.getString("img");
        }
        catch (JSONException e){
            this.img = "";
        }
        try {
            this.rarity = jsoncard.getString("rarity");
        }
        catch (JSONException e){
            this.rarity = "";
        }

        this.cardSet = jsoncard.getString("cardSet");
    }


    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Integer getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Integer id) {
        this.idCarte = idCarte;
    }

    public String getNameCarte() {
        return nameCarte;
    }

    public void setNameCarte(String name) {
        this.nameCarte = name;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "idCarte=" + idCarte +
                ", name='" + nameCarte + '\'' +
                '}';
    }



}
