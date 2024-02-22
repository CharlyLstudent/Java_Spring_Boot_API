package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;

public interface GamePluginInterface {
    Game createGame();
    String getName(Locale locale);
}
