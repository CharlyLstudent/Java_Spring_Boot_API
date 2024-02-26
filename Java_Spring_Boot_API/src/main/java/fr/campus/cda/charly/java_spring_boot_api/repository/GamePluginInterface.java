package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;

public interface GamePluginInterface {
    Game createGame(GameCreationParams params);
    boolean supportsGameType(String type);
    String getName(Locale locale);
}
