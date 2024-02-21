package fr.campus.cda.charly.java_spring_boot_api.dto;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Map;
import java.util.UUID;

public record GameStatusDTO(String factoryId, int boardSize, GameStatus gameStatus, UUID gameId) {

}
