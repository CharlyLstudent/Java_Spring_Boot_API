package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameMovesDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Collection;
import java.util.Optional;

public interface GameCatalogInterface {
    Collection<String> getGameIdentifiers();

    GameFactory selectFactory(String type);

}
