package src.service;

import java.util.Collections;
import java.util.List;

import src.model.Game;
import src.model.Player;
import src.model.enums.GameState;

public class GameService {

    public Game createGame(int size, List<Player> players) {
        return Game.builder()
                .size(size)
                .players(players)
                .build();
    }

    public Game startGame(Game game) {
        game.setGameState(GameState.IN_PROGRESS);
        List<Player> players = game.getPlayers();
        Collections.shuffle(players);
        game.setPlayers(players);
        return game;
    }

}
