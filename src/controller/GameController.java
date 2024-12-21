package src.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.model.Board;
import src.model.Game;
import src.model.Move;
import src.model.Player;
import src.service.GameService;
import src.service.PlayerService;
import src.service.WinnerService;

public class GameController {
    private PlayerService playerService;
    private Scanner sc;
    private List<Player> players;
    private GameService gameService;

    public GameController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.players = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public List<Player> generatePlayers(int size) {
        try {
            System.out.println("Please enter player details.");
            for (int i = 0; i < size - 1; i++) {
                System.out.println("Player " + (i + 1) + " Name : ");
                String name = sc.nextLine();
                System.out.println("Player " + (i + 1) + " Symbol : ");
                char symbol = sc.nextLine().charAt(0);
                players.add(playerService.createPlayer(name, symbol));
            }
        } catch (Exception e) {
            System.out.println("Error occured while generating players : " + e);
        }
        return players;
    }

    public Move createMove(Player player, Game game) {
        System.out.println("Please enter row number : ");
        int row = sc.nextInt();
        System.out.println("Please enter the col number : ");
        int col = sc.nextInt();

        if (row < 0 || row >= game.getBoard().getSize() || col < 0 || col >= game.getBoard().getSize()) {
            throw new RuntimeException("Invalid value for row and column");
        }
        try {
            return gameService.executeMove(player, game, row, col);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Player checkWinner(Board board, Move move, WinnerService winnerService) {
        return winnerService.checkWinner(board, move);
    }
}
