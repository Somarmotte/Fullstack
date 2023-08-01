package com.hsapi.apideck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;


@Entity
public class Carte {

    @Id @GeneratedValue
    public Integer idCarte;



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

        this.cardSet = jsoncard.getString("cardSet");
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
