package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CatalogController {
    private GameCatalogInterface gameCatalog;

    public CatalogController(GameCatalogInterface gameCatalog) {
        this.gameCatalog = gameCatalog;
    }

    @GetMapping("/catalog")
    public Collection<String> getGameTypes(){
        return gameCatalog.getGameIdentifiers();
    }
}
