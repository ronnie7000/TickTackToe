package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.model.Game;
import src.model.Player;
import src.service.BoardService;
import src.service.GameService;
import src.service.PlayerService;

public class Main {
    public static void main(String[] args) {

        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();
        BoardService boardService = new BoardService();
        Scanner sc = new Scanner(System.in);

        System.out.println("WELCOME TO TIC TAC TOE GAME.");
        
        System.out.println("Please enter the size of the board : ");
        int size = sc.nextInt();

        List<Player> players = new ArrayList<>();
        System.out.println("Please enter player details.");

        for(int i = 0; i < size-1; i++) {
            System.out.println("Player "+ (i+1) +" Name : " );
            String name = sc.nextLine();
            name = sc.nextLine();
            System.out.println("Player "+ (i+1) +" Symbol : " );
            char symbol = sc.nextLine().charAt(0);
            players.add(playerService.createPlayer(name, symbol));
        }

        Game game = gameService.createGame(size, players);
        game = gameService.startGame(game);
        boardService.printBoard(game.getBoard());        

        sc.close();
    }
}