package src;

import java.util.List;
import java.util.Scanner;

import src.controller.GameController;
import src.exception.GameDrawnException;
import src.model.Game;
import src.model.Move;
import src.model.Player;
import src.model.enums.GameState;
import src.service.BoardService;
import src.service.BotPlayingStrategyImpl;
import src.service.GameService;
import src.service.PlayerService;

public class Main {
    public static void main(String[] args) {
        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();
        BoardService boardService = new BoardService();
        BotPlayingStrategyImpl botPlayingStrategy = new BotPlayingStrategyImpl();
        GameController gameController = new GameController(playerService, gameService, botPlayingStrategy);
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("WELCOME TO TIC TAC TOE GAME.");

            System.out.println("Please enter the size of the board : ");
            int size = sc.nextInt();

            List<Player> players = gameController.generatePlayers(size);

            Game game = gameService.createGame(size, players);
            game = gameService.startGame(game);

            while (true) {
                int nextPlayerIndex = game.getNextPlayerIndex();
                Player currentPlayer = game.getPlayers().get(nextPlayerIndex);

                System.out.println("Player to make a move : " + currentPlayer.getName());
                // System.out.println("Do you want to undo> If yes enter the number of steps or else enter 0 : ");
                // int undoMoves = sc.nextInt();

                // if (undoMoves != 0) {
                //     game = gameController.undoMoves(undoMoves, game);
                // }

                boardService.printBoard(game.getBoard());
                System.out.println();

                Move move = gameController.createMove(currentPlayer, game);
                try {
                    if (move == null) {
                        System.out.println("Please enter valid cell details");
                        continue;
                    }
                    Player winner = gameController.checkWinner(game.getBoard(), move, game.getWinnerService());
                    if (winner != null) {
                        game.setGameState(GameState.WINNER_DONE);
                        System.out.println("Winner is " + winner.getName());
                        boardService.printBoard(game.getBoard());

                        System.out.println("Do you want a replay? Pres 1 for yes or 0 for no.");
                        int replay = sc.nextInt();

                        if (replay == 1) {
                            gameController.getReplay(game);
                        }
                        break;
                    }
                } catch (GameDrawnException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                game.setNextPlayerIndex((game.getNextPlayerIndex() + 1) % players.size());
            }
        } catch (Exception e) {
            System.out.println("Error occured : " + e);
        } finally {
            sc.close();
        }
    }
}