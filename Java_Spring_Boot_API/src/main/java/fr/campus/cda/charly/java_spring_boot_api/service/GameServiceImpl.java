package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.repository.GamePluginInterface;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameServiceInterface;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameServiceInterface {

    @Autowired
    private GameCatalogInterface gameCatalog;

    private final Map<String, Game> games = new HashMap<>();
    public Game createGame(GameCreationParams params) {
        GameFactory gameFactory = gameCatalog.selectFactory(params.getGameType());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        games.put(game.getId().toString(),game);
        return game;
    }

    @Override
    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    @Override
    public Collection<Game> getAllGames() {
        return games.values();
    }

    @Override
    public boolean deleteGame(String gameId) {
        if (games.containsKey(gameId)) {
            games.remove(gameId);
            return true; // Retourne true si le jeu est trouvé et supprimé
        }
        return false; // Retourne false si le jeu n'est pas trouvé
    }

    @Override
    public Object getPossibleMoves(String gameId) {
        return games.get(gameId).getRemainingTokens();
    }

}
