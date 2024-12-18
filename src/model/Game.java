package src.model;

import java.util.ArrayList;
import java.util.List;

import src.exception.InvalidGameParamsException;
import src.model.enums.GameState;

public class Game {
    
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private int nextPlayerIndex;
    private Player winnerPlayer;
    private List<Board> oldBoards;
    private List<Move> oldMoves;

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public List<Board> getOldBoards() {
        return oldBoards;
    }

    public List<Move> getOldMoves() {
        return oldMoves;
    }
    

    public static Builder builder() {
        return new Builder();
    }


    private Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.gameState = GameState.YET_TO_START;
        this.nextPlayerIndex = 0;
        this.winnerPlayer = null;
        this.oldBoards = new ArrayList<>();
        this.oldMoves = new ArrayList<>();
    }


    public static class Builder {
        private int size;
        private List<Player> players;

        public Builder size(int size) {
            this.size = size;
            return this;
        }
        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build(Builder builder) {
            validate();
            return new Game(new Board(size), players);
        }

        private void validate() {
            if(size < 3 || size > 10) {
                throw new InvalidGameParamsException("Board size must be between 3 and 10");
            } 
            if(players.size() != size - 1) {
                throw new InvalidGameParamsException("Total number of players must be " + (size - 1));
            }
        }

    }

           
}
