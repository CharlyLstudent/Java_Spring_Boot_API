package fr.campus.cda.charly.java_spring_boot_api.plugin;

import fr.campus.cda.charly.java_spring_boot_api.repository.GamePluginInterface;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin implements GamePluginInterface {
    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.taquin.default-boardsize}")
    private int defaultBoardsize;

    @Autowired
    private final MessageSource messageSource;
    @Autowired
    public TaquinPlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Game createGame() {
        return null;
    }

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.factory-id", null, locale);
    }
}
