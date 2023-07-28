package com.hsapi.apideck.exception;


import com.hsapi.apideck.model.Deck;
import com.hsapi.apideck.repo.CarteRepository;
import com.hsapi.apideck.repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class DeckFullException extends RuntimeException {

    public DeckFullException(Integer id) {
        super("Deck is full" + id);
    }
}

@Component
class DeckFullCommandLineRunner implements CommandLineRunner {
    @Autowired
    DeckRepository deckRepository;

    @Override
    public void run(String... args) throws Exception{
        for (Deck c : this.deckRepository.findAll()){
            System.out.println(c.toString());
        }
    }


}
