package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;

import java.util.Collection;

public interface GameCatalogInterface {
    Collection<String> getGameIdentifiers();

    GameStatusDTO getGameState(String gameId);
}
