package src.service;

import java.util.HashSet;
import java.util.Set;

import src.exception.InvalidPlayerParamsException;
import src.model.Player;
import src.model.enums.PlayerType;

public class PlayerService {
    
    private Set<Character> symbolSet = new HashSet<>();
    private static int id = 1;
    
    public Player createPlayer(String name, char symbol) {
        if(symbolSet.contains(symbol)) {
            throw new InvalidPlayerParamsException("This symbol is already taken. Please use different symbol.");
        }
        symbolSet.add(symbol);
        return new Player(id++, name, symbol, PlayerType.HUMAN);
    }
}
