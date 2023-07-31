package com.hsapi.apideck.services;

import com.hsapi.apideck.model.Carte;



import org.springframework.stereotype.Service;
import com.hsapi.apideck.repo.CarteRepository;
import com.hsapi.apideck.controller.*;

import java.io.IOException;

import java.util.ArrayList;
import com.hsapi.apideck.client.*;

@Service
public class CarteService {
    public static HsApiClient ApiClient;
    private static CarteRepository repository;

    CarteService(HsApiClient client) {

        this.ApiClient = client;
        this.repository = repository;
    }

    public void triCard(ArrayList<Carte> listAllCard) throws IOException, InterruptedException {

         for (int i = 0; i < listAllCard.size(); i++) {


            if (listAllCard.get(i).getCardSet().equals("Battlegrounds") || listAllCard.get(i).getCardSet().equals("Mercenaries") || listAllCard.get(i).getCardSet().equals("Unknown")
                    || listAllCard.get(i).getCardSet().equals("Credits") || listAllCard.get(i).getCardSet().equals("Tavern Brawl") || listAllCard.get(i).getCardSet().equals("Vanilla")
                    || listAllCard.get(i).getCardSet().equals("Classic") || listAllCard.get(i).getCardSet().equals("Basic") || listAllCard.get(i).getCardSet().equals("Classic") || listAllCard.get(i).getCardSet().equals("Hero Skins")
                    || listAllCard.get(i).getImg().equals("") || listAllCard.get(i).getCardSet().equals("Legacy")){

            } else {
                CarteService.useRepoCarte(listAllCard.get(i));
            }

        }

    }



    public static Carte useRepoCarte(Carte carte1){

           return CarteController.carteRepository.save(carte1);

    }



    public ArrayList fetchCardForClass(String klass) throws IOException, InterruptedException {
        return ApiClient.getCardForClass(klass);
    }

    public ArrayList fetchCardForClassFromRepo(String klass) throws IOException, InterruptedException {
        ArrayList CardByClassAndNeutral = new ArrayList<>();
        CardByClassAndNeutral.addAll(CarteController.carteRepository.findAllByPlayerClass(klass));
        CardByClassAndNeutral.addAll(CarteController.carteRepository.findAllByPlayerClass("Neutral"));

        return CardByClassAndNeutral;
    }
    }














