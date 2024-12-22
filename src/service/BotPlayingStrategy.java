package src.service;

import src.model.Game;
import src.model.Move;
import src.model.Player;

public interface BotPlayingStrategy {
    
    Move createMove(Player player, Game game);
}
