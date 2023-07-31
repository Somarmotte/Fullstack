package com.hsapi.apideck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hsapi.apideck.model.Carte;

import java.util.ArrayList;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CarteRepository extends JpaRepository<Carte, Integer> {

    ArrayList<Carte> findAllByPlayerClass(String playerClass);

    // findAllByName(String name);

}