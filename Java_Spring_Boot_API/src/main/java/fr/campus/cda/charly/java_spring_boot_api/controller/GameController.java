package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.service.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameCatalogInterface gameCatalog;

    @Autowired
    private GameServiceImpl game;

    private GameDTO gameToDto(Game entry) {
        return new GameDTO(entry.getId().toString(), entry.getFactoryId());
    }

    private List<GameDTO> DtoToList(Collection<Game> games) {
        return games.stream()
                .map(this::gameToDto)
                .toList();
    }
    @GetMapping("/gameCatalog")
    public Collection<String> getGameCatalog() {
        return gameCatalog.getGameIdentifiers();
    }

    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameCreationParams params) {
        return gameToDto(game.createGame(params));
    }
    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable String gameId) {
        return gameToDto(game.getGame(gameId));
    }
    @GetMapping("/games/games-list")
    public List<GameDTO> getAllGames() {
        return DtoToList(game.getAllGames());
    }
    @DeleteMapping("/games/{gameId}")
    public boolean deleteGame(@PathVariable String gameId) {
        return game.deleteGame(gameId);
    }

    @GetMapping("/games/{gameId}/possiblemoves")
    public Object getPossibleMoves(@PathVariable String gameId) {
        return game.getPossibleMoves(gameId);
    }

}
