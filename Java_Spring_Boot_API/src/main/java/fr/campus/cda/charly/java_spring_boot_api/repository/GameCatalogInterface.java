package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;

import java.util.Collection;
import java.util.Optional;

public interface GameCatalogInterface {
    Collection<String> getGameIdentifiers();
    GameDTO createGame(GameCreationParams params);
    Optional<GameStatusDTO> getGameState(String gameId);
    Collection<GameStatusDTO> getAllGames();
    boolean deleteGame(String gameId);
//    List<String> possibleMoves();

}
