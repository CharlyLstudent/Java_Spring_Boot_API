package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCatalogDummyImpl implements GameCatalogInterface {
    private final TicTacToeGameFactory ticTacToeGameFactory;

    private final TaquinGameFactory taquinGameFactory;

    private final ConnectFourGameFactory connectFourGameFactory;
    private final Map<String, GameStatusDTO> games = new HashMap<>();

    public GameCatalogDummyImpl() {
        ticTacToeGameFactory = new TicTacToeGameFactory();
        taquinGameFactory = new TaquinGameFactory();
        connectFourGameFactory = new ConnectFourGameFactory();
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        Set<String> gameIds = new HashSet<>();
        gameIds.add(ticTacToeGameFactory.getGameId());
        gameIds.add(taquinGameFactory.getGameId());
        gameIds.add(connectFourGameFactory.getGameId());
        return Collections.unmodifiableSet(gameIds);
    }

    public void addGame(String gameId, GameStatusDTO game) {
        games.put(gameId, game);
    }
    @Override
    public GameStatusDTO getGameState(String gameId) {
        return games.get(gameId);
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