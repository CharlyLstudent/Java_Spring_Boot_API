package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.service.GameCatalogDummyImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    private GameCatalogDummyImpl gameCatalog;

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {
        String gameId = UUID.randomUUID().toString(); // Générez un ID unique pour le jeu
        GameFactory gameFactory = gameCatalog.selectFactory(params.getGameType());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        gameCatalog.addGame(gameId, new GameStatusDTO(game.getFactoryId(), game.getBoardSize(), game.getStatus()));
        return new GameDTO(game.getFactoryId(), game.getBoardSize(), gameId);
    }

    @GetMapping("/gameid")
    public Collection<String> getGameId() {
        return gameCatalog.getGameIdentifiers();
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<GameStatusDTO> getGame(@PathVariable String gameId) {
        GameStatusDTO gameDto = gameCatalog.getGameState(gameId); // Utilisation de la méthode du service
        if (gameDto == null) {
            return ResponseEntity.notFound().build(); // Si le jeu n'est pas trouvé, retourne 404
        }
        return ResponseEntity.ok(gameDto);

    }

    @PostMapping("/games/{gameId}/move")
    public void moveToken(@PathVariable String gameId) {

    }

    @PostMapping("/games/{gameId}/save")
    public void saveGame(@PathVariable String gameId) {

    }

    @GetMapping("/games/{gameId}/history")
    public void getMovesHistory(@PathVariable String gameId) {

    }

    @GetMapping("/games/history")
    public void getGamesHistory() {

    }

    @DeleteMapping("/games/{gameId}")
    public void deleteGame(@PathVariable String gameId) {

    }
}
