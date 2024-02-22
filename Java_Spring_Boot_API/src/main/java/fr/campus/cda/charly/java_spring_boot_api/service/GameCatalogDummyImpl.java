package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameMovesDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.Token;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCatalogDummyImpl implements GameCatalogInterface {
    private final TicTacToeGameFactory ticTacToeGameFactory;

    private final TaquinGameFactory taquinGameFactory;

    private final ConnectFourGameFactory connectFourGameFactory;



    public GameCatalogDummyImpl() {
        ticTacToeGameFactory = new TicTacToeGameFactory();
        taquinGameFactory = new TaquinGameFactory();
        connectFourGameFactory = new ConnectFourGameFactory();
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameFactoryId(),taquinGameFactory.getGameFactoryId(),connectFourGameFactory.getGameFactoryId());
    }


    public GameFactory selectFactory(String type) {

        return switch (type) {
            case "TicTacToe" -> ticTacToeGameFactory;
            case "Taquin" -> taquinGameFactory;
            case "ConnectFour" -> connectFourGameFactory;
            default -> throw new IllegalArgumentException("Invalid game type");
        };
    }
}