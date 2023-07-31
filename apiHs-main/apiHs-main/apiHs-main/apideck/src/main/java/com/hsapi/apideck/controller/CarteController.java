package com.hsapi.apideck.controller;


import com.hsapi.apideck.client.HsApiClient;
import com.hsapi.apideck.exception.CarteNotFoundException;
import com.hsapi.apideck.model.Carte;
import com.hsapi.apideck.repo.CarteRepository;

import org.springframework.web.bind.annotation.*;
import com.hsapi.apideck.services.*;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarteController {

    public static CarteRepository carteRepository;
    public static CarteService carteService;


    CarteController(CarteRepository repository, CarteService service) {
        this.carteRepository = repository;
        this.carteService= service;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/cartes2")
    ArrayList all(@RequestParam(required = false) String toto) throws IOException, InterruptedException {
        if(toto != null) {
            return carteService.fetchCardForClass(toto);
        }

      //  return carteService.mapJSONArrayToCardArray();
        carteService.triCard(CarteService.ApiClient.getAllCards());
        return CarteService.ApiClient.getAllCards();

        }



    @PostMapping("/cartes")
    Carte newCarte(@RequestBody Carte newCarte) {
        return carteRepository.save(newCarte);
    }

    // Single items

    @GetMapping("/cartes/{id}")
    Carte one(@PathVariable Integer id) {

        return carteRepository.findById(id)
                .orElseThrow(() -> new CarteNotFoundException(id));
    }

    @PutMapping("/cartes/{id}")
    Carte replaceCarte(@RequestBody Carte newCarte, @PathVariable Integer id) {

        return carteRepository.findById(id)
                .map(carte -> {
                    carte.setNameCarte(newCarte.getNameCarte());
                    return carteRepository.save(carte);
                })
                .orElseGet(() -> {
                    newCarte.setIdCarte(id);
                    return carteRepository.save(newCarte);
                });
    }

    @DeleteMapping("/cartes/{id}")
    void deleteCarte(@PathVariable Integer id) {
        carteRepository.deleteById(id);
    }

     @GetMapping("/cartes/playerclass/{playerClass}")

    List<Carte> one(@PathVariable String playerClass) throws IOException, InterruptedException {

        return carteService.fetchCardForClassFromRepo(playerClass);

    }

}