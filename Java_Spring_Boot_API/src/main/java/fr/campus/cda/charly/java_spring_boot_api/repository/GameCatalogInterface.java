package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GameCatalogInterface {
    Collection<String> getGameIdentifiers();

    Optional<GameStatusDTO> getGameState(String gameId);
    Collection<GameStatusDTO> getAllGames();
    void deleteGame(String gameId);
//    List<String> possibleMoves();

}
