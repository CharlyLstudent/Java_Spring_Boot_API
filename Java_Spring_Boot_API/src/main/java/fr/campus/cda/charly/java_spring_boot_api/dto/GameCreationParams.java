package fr.campus.cda.charly.java_spring_boot_api.dto;

public class GameCreationParams {
    private String gameType;
    private int playerCount;
    private int boardSize;

    public GameCreationParams() {

    }

    public String getGameType() {
        return gameType;
    }


    public int getPlayerCount() {
        return playerCount;
    }


    public int getBoardSize() {
        return boardSize;
    }

}
