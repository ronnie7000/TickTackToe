package src.model;

import src.model.enums.DifficultyLevel;
import src.model.enums.PlayerType;

public class BotPlayer extends Player{
    DifficultyLevel level;

    
    public BotPlayer(int id, String name, char symbol, PlayerType playerType, DifficultyLevel level) {
        super(id, name, symbol, playerType);
        this.level = level;
    }

}
