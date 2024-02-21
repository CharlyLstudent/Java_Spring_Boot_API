package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.service.GameCatalogDummyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CatalogController {
    @Autowired
    private GameCatalogDummyImpl gameCatalog;
    
    @GetMapping("/gameid")
    public Collection<String> getGameId() {
        return gameCatalog.getGameIdentifiers();
    }
}
