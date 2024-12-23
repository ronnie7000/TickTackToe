package src.service;

import java.util.Collections;
import java.util.List;

import src.model.Board;
import src.model.Cell;
import src.model.Game;
import src.model.Move;
import src.model.Player;
import src.model.enums.CellState;
import src.model.enums.GameState;

public class GameService {

    private BoardService boardService;

    public GameService() {
        boardService = new BoardService();
    }

    public Game createGame(int size, List<Player> players) {
        return Game.builder()
                .size(size)
                .players(players)
                .winnerService(new WinnerService(size))
                .build();
    }

    public Game startGame(Game game) {
        game.setGameState(GameState.IN_PROGRESS);
        List<Player> players = game.getPlayers();
        Collections.shuffle(players);
        game.setPlayers(players);
        return game;
    }

    public Move executeMove(Player player, Game game, int row, int col) {
        Cell cell = game.getBoard().getCells().get(row).get(col);
        if (cell.getCellState() != CellState.EMPTY) {
            throw new RuntimeException("Given cell can not be played.");
        }
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(player);
        Move move = new Move(cell, player);
        game.getOldMoves().add(move);
        game.getOldBoards().add(game.getBoard().clone());

        return move;
    }

    public Game undoMoves(int undoMovesCount, Game game) {
        List<Move> oldMoves = game.getOldMoves();
        List<Board> oldBoards = game.getOldBoards();

        oldMoves = oldMoves.subList(0, undoMovesCount);
        oldBoards = oldBoards.subList(0, undoMovesCount);

        game.setBoard(oldBoards.get(oldBoards.size() - 1));
        return game;
    }

    public void getReplay(Game game) {
        for (Board board : game.getOldBoards()) {
            boardService.printBoard(board);
            System.out.println();
        }
    }

}
