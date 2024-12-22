package src.service;

import java.util.List;

import src.model.Board;
import src.model.Cell;
import src.model.Game;
import src.model.Move;
import src.model.Player;
import src.model.enums.CellState;

public class BotPlayingStrategyImpl implements BotPlayingStrategy{

    public Move createMove(Player player, Game game) {
        Board board = game.getBoard();

        for(List<Cell> cells : board.getCells()) {
            for(Cell cell : cells) {
                if(CellState.EMPTY.equals(cell.getCellState())) {
                    cell.setCellState(CellState.FILLED);
                    cell.setPlayer(player);
                    Move move = new Move(cell, player);
                    game.getOldMoves().add(move);
                    game.getOldBoards().add(board.clone());
                    return move;
                }
            }
        }
        return null;
    }
    
}
