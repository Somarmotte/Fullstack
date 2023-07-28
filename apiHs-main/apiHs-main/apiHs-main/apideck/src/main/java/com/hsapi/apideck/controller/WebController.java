package com.hsapi.apideck.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class WebController {

    @GetMapping("/front")

    public String home(Model model) {

// Vous pouvez ajouter des données au modèle ici, si nécessaire

        model.addAttribute("message", "Hello, World!");

        return "PageAcceuil"; // Cela renverra à un fichier "index.html" dans les ressources/templates

    }

}

