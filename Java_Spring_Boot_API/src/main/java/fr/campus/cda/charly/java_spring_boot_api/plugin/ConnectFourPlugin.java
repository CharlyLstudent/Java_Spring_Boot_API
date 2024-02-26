package fr.campus.cda.charly.java_spring_boot_api.plugin;

import fr.campus.cda.charly.java_spring_boot_api.dto.GameCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.repository.GameCatalogInterface;
import fr.campus.cda.charly.java_spring_boot_api.repository.GamePluginInterface;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConnectFourPlugin implements GamePluginInterface {
    @Value("${game.connectfour.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.connectfour.default-boardsize}")
    private int defaultBoardsize;

    @Autowired
    private GameCatalogInterface gameCatalog;

    @Autowired
    private final MessageSource messageSource;

    @Autowired
    public ConnectFourPlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public Game createGame(GameCreationParams params) {
        GameFactory gameFactory = gameCatalog.selectFactory(params.getGameType());
        return gameFactory.createGame(defaultPlayerCount, defaultBoardsize);
    }

    @Override
    public boolean supportsGameType(String type) {
        return "ConnectFour".equalsIgnoreCase(type);
    }

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connectfour.factory-id", null, locale);
    }
}
