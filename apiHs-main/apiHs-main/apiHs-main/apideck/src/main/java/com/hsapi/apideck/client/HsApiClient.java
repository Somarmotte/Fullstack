package com.hsapi.apideck.client;

import com.hsapi.apideck.model.Carte;
import com.hsapi.apideck.repo.CarteRepository;
import com.hsapi.apideck.services.CarteService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;


@Component
public class HsApiClient {




    public ArrayList<Carte> getAllCards() throws IOException, InterruptedException {
        ArrayList<Carte> allCards = new ArrayList<>();
        allCards.addAll(getCardForClass("Paladin"));
        allCards.addAll(getCardForClass("Priest"));
        allCards.addAll(getCardForClass("Warlock"));
        allCards.addAll(getCardForClass("Warrior"));
        allCards.addAll(getCardForClass("Demon%20Hunter"));
        allCards.addAll(getCardForClass("Shaman"));
        allCards.addAll(getCardForClass("Mage"));
        allCards.addAll(getCardForClass("Rogue"));
        allCards.addAll(getCardForClass("Hunter"));
        allCards.addAll(getCardForClass("Death%20Knight"));
        allCards.addAll(getCardForClass("Druid"));
        allCards.addAll(getCardForClass("Neutral"));
        return allCards;
    }

    public ArrayList<Carte> getCardForClass(String playerClass) throws IOException, InterruptedException {
        HttpRequest requestCard = HttpRequest.newBuilder()
            .uri(URI.create("https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/classes/" + playerClass))
             .header("X-RapidAPI-Key", "07ba252d7emshde196276763257dp1af5b2jsn7144090c0ea7")
            .header("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
             .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
           HttpResponse<String> responseCard = HttpClient.newHttpClient().send(requestCard, HttpResponse.BodyHandlers.ofString());
           JSONArray JACard = new JSONArray(responseCard.body());
           ArrayList<Carte> cartes = new ArrayList<>();
           for(int i = 0; i < JACard.length(); i++) {
               cartes.add(new Carte((JSONObject) JACard.get(i)));
           }

           return cartes;
    }



}
