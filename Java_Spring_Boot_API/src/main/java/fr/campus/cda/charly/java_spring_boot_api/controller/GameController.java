package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.service.GameCatalogDummyImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private GameCatalogDummyImpl gameCatalog;

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {
        return gameCatalog.createGame(params);
    }
    @GetMapping("/games/{gameId}")
    public GameStatusDTO getGame(@PathVariable String gameId) {
        return gameCatalog
                .getGameState(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/games/games-list")
    public Collection<GameStatusDTO> getAllGames() {
        return gameCatalog.getAllGames();
    }
    @DeleteMapping("/games/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
        gameCatalog.deleteGame(gameId);
        return ResponseEntity.ok("La partie a bien été supprimée !");
    }
}
