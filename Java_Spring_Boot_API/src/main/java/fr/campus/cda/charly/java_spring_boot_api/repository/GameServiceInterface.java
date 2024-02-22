package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;

public interface GameServiceInterface {
    Game createGame(GameCreationParams params);
    Collection<Game> getAllGames();
    Game getGame(String gameId);

    boolean deleteGame(String gameId);

    Object getPossibleMoves(String gameId);

}
