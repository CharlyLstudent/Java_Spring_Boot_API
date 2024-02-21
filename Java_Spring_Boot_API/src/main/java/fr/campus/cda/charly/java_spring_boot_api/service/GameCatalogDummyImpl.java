package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.GameStatusDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.le_campus_numerique.square_games.engine.Game;
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
        gameIds.add(ticTacToeGameFactory.getGameFactoryId());
        gameIds.add(taquinGameFactory.getGameFactoryId());
        gameIds.add(connectFourGameFactory.getGameFactoryId());
        return Collections.unmodifiableSet(gameIds);
    }

    @Override
    public Optional<GameStatusDTO> getGameState(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    @Override
    public Collection<GameStatusDTO> getAllGames() {
        return games.values();
    }

    @Override
    public boolean deleteGame(String gameId) {
        if (games.containsKey(gameId)) {
            games.remove(gameId);
            return true; // Retourne true si le jeu est trouvé et supprimé
        }
        return false; // Retourne false si le jeu n'est pas trouvé
    }

//    @Override
//    public List<String> possibleMoves(String gameId) {
//
//        return
//    }

    public GameDTO createGame(GameCreationParams params) {
        GameFactory gameFactory = selectFactory(params.getGameType());
        Game game = gameFactory.createGame(params.getPlayerCount(), params.getBoardSize());
        UUID gameId = game.getId(); // Utilisez l'ID fourni par l'objet game
        GameDTO creatGame = new GameDTO(game.getFactoryId(), game.getBoardSize(), game.getStatus(), game.getId());
        games.put(gameId.toString(), new GameStatusDTO(game.getFactoryId(), game.getBoardSize(), game.getStatus(), game.getId()));
        return creatGame;
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